package org.bookmate.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bookmate.dao.BookDao;
import org.bookmate.entities.Book;
import org.bookmate.entities.BookClassifyTwo;
import org.bookmate.entities.BookRecommendResult;
import org.bookmate.entities.UserRecommendResult;
import org.bookmate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * 图书相关业务逻辑实现类
 * @author yangyuhao
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public Book getBookById(Integer id) {
		return bookDao.selectBookById(id);
	}

	@Override
	public void addBook(Integer bookNumber, String bookName, String bookAuthor, String bookPress, String bookImageBig,
			String bookImageSmall, String bookClassifyOne, String bookClassifyTwo, String bookDesc) {
		Book book = new Book();
		book.setBookNumber(bookNumber);
		book.setBookName(bookName);
		book.setBookAuthor(bookAuthor);
		book.setBookPress(bookPress);
		book.setBookImageBig(bookImageBig);
		book.setBookImageSmall(bookImageSmall);
		book.setBookClassifyOne(bookClassifyOne);
		book.setBookClassifyTwo(bookClassifyTwo);
		book.setBookDesc(bookDesc);
		bookDao.insertBook(book);
	}
	
	@Override
	public void addBook2(Integer bookNumber, String bookName, String bookAuthor, String bookPress, String bookImageBig,
			String bookImageSmall, String bookClassifyOne, String bookClassifyTwo, String bookDesc, String bookAddress,
			Integer bookSum, Integer bookResidue, Double bookGrade) {
		if (!"".equals(bookName) && !"".equals(bookClassifyOne)) {
			Book book = new Book();
			book.setBookNumber(bookNumber);
			book.setBookName(bookName);
			book.setBookAuthor(bookAuthor);
			book.setBookPress(bookPress);
			book.setBookImageBig(bookImageBig);
			book.setBookImageSmall(bookImageSmall);
			book.setBookClassifyOne(bookClassifyOne);
			book.setBookClassifyTwo(bookClassifyTwo);
			book.setBookDesc(bookDesc);
			book.setBookAddress(bookAddress);
			book.setBookSum(bookSum);
			book.setBookResidue(bookResidue);
			book.setBookGrade(bookGrade);
			bookDao.insertBook2(book);
		}
	}

	@Override
	public List<String> getAllClassifyOne() {
		return bookDao.selectAllClassifyOne();
	}

	@Override
	public List<Book> getBookByClassifyOne(String classifyOne) {
		return bookDao.selectBookByClassifyOne(classifyOne);
	}

	@Override
	public void addBookAddressAndGrade() {
		ArrayList<Book> books = (ArrayList<Book>) bookDao.selectAllBook();
		for (int i = 0; i < books.size(); ++i) {
			char[] c = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
			int r1 = new Random().nextInt(7);
			char a1 = c[r1];
			int a2 = new Random().nextInt(9);
			int a3 = new Random().nextInt(9);
			int a4 = new Random().nextInt(9);
			int a5 = new Random().nextInt(9);
			String address = String.valueOf(a1) + String.valueOf(a2) + String.valueOf(a3) + String.valueOf(a4) + String.valueOf(a5);
			int g1 = new Random().nextInt(5);
			if (!"".equals(books.get(i).getBookDesc())) {
				g1 = 4;
			}
			int g2 = new Random().nextInt(9);
			double grade = g1 + g2 * 0.1;
			if (grade < 1) {
				grade = grade + 1;
			}
			if (grade >= 5) {
				grade = 5;
			}
			BigDecimal b = new BigDecimal(grade);  
			grade = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();  
			books.get(i).setBookAddress(address);
			books.get(i).setBookGrade(grade);
			bookDao.updateBook(books.get(i));
			System.out.println(i + " - " + grade);
		}
	}

	@Override
	public void addBookSumAndResidue() {
		ArrayList<Book> books = (ArrayList<Book>) bookDao.selectAllBook();
		for (int i = 0; i < books.size(); ++i) {
			int m = new Random().nextInt(15);
			books.get(i).setBookSum(m);
			books.get(i).setBookResidue(m);
			bookDao.updateBook(books.get(i));
			System.out.println(i + " - " + m);
		}
	}

	@Override
	public List<Book> getBookByNameLike(String bookName) {
		return bookDao.selectBookByNameLike(bookName);
	}

	@Override
	public List<Book> getAllBook() {
		return bookDao.selectAllBook();
	}

	@Override
	public void insertBookClassifyTwo() {
		ArrayList<String> list = (ArrayList<String>) bookDao.selectAllClassifyTwo();
		for (int i = 0; i < list.size(); ++i) {
			BookClassifyTwo bookClassifyTwo = new BookClassifyTwo();
			bookClassifyTwo.setBookClassifyOneId(i + 1);
			bookClassifyTwo.setBookClassifyOneName(list.get(i));
			bookDao.insertClassifyTwo(bookClassifyTwo);
			System.out.println(i);
		}
	}

	@Override
	public Integer getBookCount() {
		return bookDao.selectBookCount();
	}

	@Override
	public void updateBook(Integer bookId, Integer bookNumber, String bookName, String bookAuthor, String bookPress,
			String bookImageBig, String bookImageSmall, String bookClassifyOne, String bookClassifyTwo, String bookDesc,
			String bookAddress, Integer bookSum, Integer bookResidue) {
		Book book = bookDao.selectBookById(bookId);
		book.setBookNumber(bookNumber);
		book.setBookName(bookName);
		book.setBookAuthor(bookAuthor);
		book.setBookPress(bookPress);
		book.setBookImageBig(bookImageBig);
		book.setBookImageSmall(bookImageSmall);
		book.setBookClassifyOne(bookClassifyOne);
		book.setBookClassifyTwo(bookClassifyTwo);
		book.setBookDesc(bookDesc);
		book.setBookAddress(bookAddress);
		book.setBookSum(bookSum);
		book.setBookResidue(bookResidue);
		bookDao.updateBook(book);
	}

	@Override
	public void deleteOneBook(Integer id) {
		Book book = bookDao.selectBookById(id);
		if (book.getBookSum() > 0 && book.getBookResidue() > 0) {
			book.setBookSum(book.getBookSum() - 1);
			book.setBookResidue(book.getBookResidue() - 1);
			bookDao.updateBook(book);
		}
	}

	@Override
	public List<Book> getHotBook() {
		return bookDao.selectHotBook();
	}

	@Override
	public List<Book> getRecommendBook(Integer userId) {
		UserRecommendResult userRecommendResult = bookDao.selectUserRecommendResultByUser(userId);
		Book book1 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR1());
		Book book2 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR2());
		Book book3 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR3());
		Book book4 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR4());
		Book book5 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR5());
		Book book6 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR6());
		Book book7 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR7());
		Book book8 = bookDao.selectBookById(userRecommendResult.getUserRecommendResultR8());
		ArrayList<Book> recommendBooks = new ArrayList<>();
		recommendBooks.add(book1);
		recommendBooks.add(book2);
		recommendBooks.add(book3);
		recommendBooks.add(book4);
		recommendBooks.add(book5);
		recommendBooks.add(book6);
		recommendBooks.add(book7);
		recommendBooks.add(book8);
		return recommendBooks;
	}

	@Override
	public List<Book> getNewBook() {
		return bookDao.selectNewBook();
	}

	@Override
	public List<Book> getBookRecommendBook(Integer bookId) {
		BookRecommendResult bookRecommendResult = bookDao.selectBookRecommendResultByBook(bookId);
		Book book1 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR1());
		Book book2 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR2());
		Book book3 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR3());
		Book book4 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR4());
		Book book5 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR5());
		Book book6 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR6());
		Book book7 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR7());
		Book book8 = bookDao.selectBookById(bookRecommendResult.getBookRecommendResultR8());
		ArrayList<Book> recommendBooks = new ArrayList<>();
		recommendBooks.add(book1);
		recommendBooks.add(book2);
		recommendBooks.add(book3);
		recommendBooks.add(book4);
		recommendBooks.add(book5);
		recommendBooks.add(book6);
		recommendBooks.add(book7);
		recommendBooks.add(book8);
		return recommendBooks;
	}
	
}
