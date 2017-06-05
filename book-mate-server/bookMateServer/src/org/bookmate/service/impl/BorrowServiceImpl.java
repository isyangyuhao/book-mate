package org.bookmate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bookmate.dao.BookDao;
import org.bookmate.dao.BorrowDao;
import org.bookmate.dao.UserDao;
import org.bookmate.entities.Book;
import org.bookmate.entities.BookClassifyTwo;
import org.bookmate.entities.Borrow;
import org.bookmate.entities.BorrowRecord;
import org.bookmate.entities.User;
import org.bookmate.service.BookService;
import org.bookmate.service.BorrowService;
import org.bookmate.service.RecordService;
import org.bookmate.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * 借书相关业务逻辑实现类
 * @author yangyuhao
 */
@Service
public class BorrowServiceImpl implements BorrowService {

	@Autowired
	private BorrowDao borrowDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean addBorrow(Integer bookId, Integer userId) {
		
		//将过期的预订记录清除
		ArrayList<Borrow> allReserveBorrowList = (ArrayList<Borrow>) borrowDao.selectAllReserveBorrow();
		long nowTime = System.currentTimeMillis();
		for (int i = 0; i < allReserveBorrowList.size(); ++i) {
			long reserveTime = allReserveBorrowList.get(i).getBorrowStartTime().getTime();
			int distanceDay = (int) ((nowTime - reserveTime) / 1000 / 60 / 60 / 24);
			if (distanceDay > 1) {
				borrowDao.deleteBorrowById(allReserveBorrowList.get(i).getBorrowId());
			}
		}
		
		//如果请求的数据不符合要求则借书失败
		if (bookId == null || userId == null) {
			return false;
		}
		
		//如果图书可借数量少于1本并且没有预订的情况下借阅失败
		Book book = bookDao.selectBookById(bookId);
		if (book.getBookResidue() < 1) {
			if (this.getBorrowByUserAndBookAndStatus(bookId, userId, -1).size() < 1) {
				return false;
			}
		}
		
		//如果存在该书尚未激活的借阅记录,则删除掉
		if (this.getBorrowByUserAndBookAndStatus(bookId, userId, 0).size() != 0) {
			this.removeBorrowByUserAndBookAndStatus(bookId, userId, 0);
		}
		
		//查看是否超过用户对应可借数量
		User user = userDao.selectUserById(userId);
		int allowNumber;
		if (user.getUserCredit() >= 60 && user.getUserCredit() <= 70) {
			allowNumber = 1;
		} else if (user.getUserCredit() < 60) {
			allowNumber = 0;
		} else if (user.getUserCredit() >= 90) {
			allowNumber = 5;
		} else {
			allowNumber = 2;
		}
		if ((this.getBorrowByUserAndStatus(userId, 1).size() 
				+ this.getBorrowByUserAndStatus(userId, 3).size()) >= allowNumber) {
			return false;
		}
		
		//如果当前书已经全部被预订则借阅失败,并且当前用户没有预订该书
		
		
		//保存借阅记录
		Borrow borrow = new Borrow();
		borrow.setBorrowBookId(bookId);
		borrow.setBorrowUserId(userId);
		borrow.setBorrowStartTime(new Date());
		borrow.setBorrowEndTime(null);
		borrow.setBorrowGrade(null);
		borrow.setBorrowStatus(0);
		borrowDao.insertBorrow(borrow);
		
		//记录借阅类别数据
		recordService.recordBorrow(userId, bookId);
        		
		return true;
	}

	@Override
	public List<Borrow> getBorrowByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status) {
		return borrowDao.selectBorrowByUserAndBookAndStatus(userId, bookId, status);
	}

	@Override
	public void removeBorrowByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status) {
		borrowDao.deleteBorrowByUserAndBookAndStatus(userId, bookId, status);
	}

	@Override
	public List<Borrow> getBorrowByUserAndStatus(Integer userId, Integer status) {
		return borrowDao.selectBorrowByUserAndStatus(userId, status);
	}

	@Override
	public Borrow getBorrowById(Integer id) {
		return borrowDao.selectBorrowById(id);
	}

	@Override
	public void adminAllowBorrow(Integer id) {
		//更新借阅状态与起始时间
		Borrow borrow = this.getBorrowById(id);
		borrow.setBorrowStatus(1);
		borrow.setBorrowStartTime(new Date());
		borrowDao.updateBorrow(borrow);
		//将对应图书的剩余数量减一
		Book book = bookDao.selectBookById(borrow.getBorrowBookId());
		book.setBookResidue(book.getBookResidue() - 1);
		bookDao.updateBook(book);
	}

	@Override
	public int returnBook(Integer userId, Integer bookId) {
		Borrow borrow = (Borrow) borrowDao.selectBorrowByUserAndBookAndStatus(userId, bookId, 1).get(0);
		return borrow.getBorrowId();
	}

	@Override
	public void adminAllowReturn(Integer id) {
		//更新借阅状态和还书时间
		Borrow borrow = borrowDao.selectBorrowById(id);
		borrow.setBorrowStatus(2);
		borrow.setBorrowEndTime(new Date());
		borrowDao.updateBorrow(borrow);
		//将对应图书的剩余数量加一
		Book book = bookDao.selectBookById(borrow.getBorrowBookId());
		book.setBookResidue(book.getBookResidue() + 1);
		bookDao.updateBook(book);
	}

	@Override
	public void gradeBook(Integer borrowId, Integer grade) {
		Borrow borrow = borrowDao.selectBorrowById(borrowId);
		borrow.setBorrowGrade(grade);
		borrowDao.updateBorrow(borrow);
		//判断用户借书时间是否超过三天
		long startTime = borrow.getBorrowStartTime().getTime();
		long endTime = borrow.getBorrowEndTime().getTime();
		int distanceDay = (int) ((endTime - startTime) / 1000 / 60 / 60 / 24);
		//如果超过三天则将对应用户的信用分加2
		if (distanceDay >= 3) {
			User user = borrow.getUser();
			int credit = user.getUserCredit();
			if (credit <= 98) {
				user.setUserCredit(credit + 2);
			} else {
				user.setUserCredit(100);
			}
			userDao.updateUser(user);
		}
		
	}

	@Override
	public void overdueReturn(Integer borrowId, Integer userId) {
		//将借阅记录状态值改为3
		Borrow borrow = borrowDao.selectBorrowById(borrowId);
		borrow.setBorrowStatus(3);
		borrowDao.updateBorrow(borrow);
		//将用户信用值减5
		User user = userDao.selectUserById(userId);
		user.setUserCredit(user.getUserCredit() - 5);
		userDao.updateUser(user);
	}

	@Override
	public Integer getBorrowCount() {
		return borrowDao.selectBorrowCount();
	}

	@Override
	public Integer getReturnCount() {
		return borrowDao.selectReturnCount();
	}

	@Override
	public void addShamBorrowData() {
		
		ArrayList<User> userList = (ArrayList<User>) userDao.selectAllUser();
		
		//遍历每一个用户
		for (int i= 0; i < userList.size(); ++i) {
			Integer userId = userList.get(i).getUserId();
			//获取当前用户的借阅记录统计
			ArrayList<BorrowRecord> borrowRecordList = 
					(ArrayList<BorrowRecord>) recordService.getBorrowRecordByUserId(userId);
			//遍历当前用户所有的借阅类别
			for (int j = 0; j < borrowRecordList.size(); ++j) {
				//当前借阅类别的数量
				int borrowNumber = borrowRecordList.get(j).getBorrowRecordNumber();
				//当前借阅类别的类别id
				Integer borrowClassifyTwoId = borrowRecordList.get(j).getBorrowRecordClassifyId();
				String borrowClassifyTwoName = 
						bookDao.selectClassifyTwoById(borrowClassifyTwoId).getBookClassifyOneName();
				//获取当前类别的所有图书
				ArrayList<Book> bookList = (ArrayList<Book>) 
						bookDao.selectBookByClassifyTwoName(borrowClassifyTwoName);
				//输出借阅记录
				for (int k = 0; k < borrowNumber; ++k) {
					try {
						Borrow borrow = new Borrow();
						borrow.setBorrowUserId(userId);
						borrow.setBorrowBookId(bookList.get(new Random().nextInt(borrowNumber)).getBookId());
						borrow.setBorrowStartTime(new Date());
						borrow.setBorrowEndTime(new Date());
						borrow.setBorrowStatus(2);
						borrow.setBorrowGrade(new Random().nextInt(5) + 1);
						borrowDao.insertBorrow(borrow);
						System.out.println(i + " / " + j + " / " + k);
					} catch (Exception e) {
						e.printStackTrace();
						continue ;
					}
				}
			}
		}
		
	}

	@Override
	public List<Borrow> getAllBorrow() {
		return borrowDao.selectAllBorrow();
	}

	@Override
	public boolean reserveBook(Integer userId, Integer bookId) {
		//如果用户或者图书为空则预订失败
		if (bookId == null || userId == null) {
			return false;
		}
		//如果图书数量少于1本则预订失败
		Book book = bookDao.selectBookById(bookId);
		if (book.getBookNumber() < 1) {
			return false;
		}
		//如果用户已经预订本书,则预订失败
		if (this.getBorrowByUserAndBookAndStatus(bookId, userId, -1).size() != 0) {
			return false;
		}
		//如果用户预订数量超过1本则预订失败
		User user = userDao.selectUserById(userId);
		int allowNumber;
		if (user.getUserCredit() >= 60 && user.getUserCredit() <= 70) {
			allowNumber = 0;
		} else if (user.getUserCredit() < 60) {
			allowNumber = 0;
		} else if (user.getUserCredit() >= 90) {
			allowNumber = 3;
		} else {
			allowNumber = 1;
		}
		if ((this.getBorrowByUserAndStatus(userId, 1).size() 
				+ this.getBorrowByUserAndStatus(userId, -1).size()) >= allowNumber) {
			return false;
		}
		//保存预订借阅记录
		Borrow borrow = new Borrow();
		borrow.setBorrowBookId(bookId);
		borrow.setBorrowUserId(userId);
		borrow.setBorrowStartTime(new Date());
		borrow.setBorrowEndTime(null);
		borrow.setBorrowGrade(null);
		borrow.setBorrowStatus(-1);
		borrowDao.insertBorrow(borrow);
		//将可借阅图书减1
		book.setBookResidue(book.getBookResidue() - 1);
		bookDao.updateBook(book);
		return true;
	}

	@Override
	public void deleteReserveBook(Integer userId, Integer bookId) {
		//将可借阅图书加1
		Book book = bookDao.selectBookById(bookId);
		book.setBookResidue(book.getBookResidue() + 1);
		bookDao.updateBook(book);
		this.removeBorrowByUserAndBookAndStatus(bookId, userId, -1);
	}

}
