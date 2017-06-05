package org.bookmate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bookmate.dao.BookDao;
import org.bookmate.dao.RecordDao;
import org.bookmate.entities.Book;
import org.bookmate.entities.BorrowRecord;
import org.bookmate.entities.BrowseRecord;
import org.bookmate.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 记录相关业务逻辑实现类
 * @author yangyuhao
 */
@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public void recordBorrow(Integer userId, Integer bookId) {
		//通过图书id获取对应二级分类id
		Book book = bookDao.selectBookById(bookId);
		String bookClassifyTwoName = book.getBookClassifyTwo();
		Integer classifyId = bookDao.selectClassifyIdByBame(bookClassifyTwoName);
		//判断当前用户是否存在当前分类的记录
		BorrowRecord borrowRecord = recordDao.selectBorrowByUserAndClassify(userId, classifyId);
		if (borrowRecord == null) {
			//插入一条新的借阅记录
			BorrowRecord newBorrowRecord = new BorrowRecord();
			newBorrowRecord.setBorrowRecordUserId(userId);
			newBorrowRecord.setBorrowRecordClassifyId(classifyId);
			newBorrowRecord.setBorrowRecordNumber(1);
			recordDao.insertBorrowRecord(newBorrowRecord);
		} else {
			//将对应借阅记录的条数加1
			recordDao.addBorrowNumberById(borrowRecord.getBorrowRecordId());
		}
		
	}

	@Override
	public void recordBrowse(Integer userId, Integer bookId) {
		//通过图书id获取对应二级分类id
		Book book = bookDao.selectBookById(bookId);
		String bookClassifyTwoName = book.getBookClassifyTwo();
		Integer classifyId = bookDao.selectClassifyIdByBame(bookClassifyTwoName);
		//判断当前用户是否存在当前分类的记录
		BrowseRecord browseRecord = recordDao.selectBrowseByUserAndClassify(userId, classifyId);
		if (browseRecord == null) {
			//插入一条新的借阅记录
			BrowseRecord newBrowseRecord = new BrowseRecord();
			newBrowseRecord.setBrowseRecordUserId(userId);
			newBrowseRecord.setBrowseRecordClassifyId(classifyId);
			newBrowseRecord.setBrowseRecordNumber(1);
			recordDao.insertBrowseRecord(newBrowseRecord);
		} else {
			//将对应借阅记录的条数加1
			recordDao.addBrowseNumberById(browseRecord.getBrowseRecordId());
		}
	}

	@Override
	public void addShamBorrowData() {
		for (int userId = 523; userId <= 1120; ++userId) {
			int randomClassifyCount = new Random().nextInt(4) + 1;  //随机关注1-4个分类
			for (int i = 0; i < randomClassifyCount; ++i) {
				int randomClassifyId = new Random().nextInt(520) + 1;  //被随机到关注分类的id
				int randomClassifyNumber = new Random().nextInt(6) + 1;  //随机借阅1-6本书
				BorrowRecord borrowRecord = new BorrowRecord();
				borrowRecord.setBorrowRecordUserId(userId);
				borrowRecord.setBorrowRecordClassifyId(randomClassifyId);
				borrowRecord.setBorrowRecordNumber(randomClassifyNumber);
				recordDao.insertBorrowRecord(borrowRecord);
				System.out.println(userId);
			}
		}
	}

	@Override
	public void updateShamBrowseData() {
		ArrayList<BrowseRecord> data = (ArrayList<BrowseRecord>) recordDao.selectAllBrowse();
		for (int i = 0; i < data.size(); ++i) {
			int randomNumber = new Random().nextInt(9) + 1;
			recordDao.updateBrowseNumber(data.get(i).getBrowseRecordId(), randomNumber);
			System.out.println(i);
		}
	}
	
	@Override
	public void addShamBrowseData() {
		for (int userId = 523; userId <= 1120; ++userId) {
			int randomClassifyCount = new Random().nextInt(2) + 1;  //随机关注1-2个分类
			for (int i = 0; i < randomClassifyCount; ++i) {
				int randomClassifyId = new Random().nextInt(520) + 1;  //被随机到关注分类的id
				int randomClassifyNumber = new Random().nextInt(6) + 1;  //随机借阅1-9本书
				BrowseRecord browseRecord = new BrowseRecord();
				browseRecord.setBrowseRecordUserId(userId);
				browseRecord.setBrowseRecordClassifyId(randomClassifyId);
				browseRecord.setBrowseRecordNumber(randomClassifyNumber);
				recordDao.insertBrowseRecord(browseRecord);
				System.out.println(userId);
			}
		}
	}

	@Override
	public BorrowRecord getBorrowRecordById(Integer id) {
		return recordDao.selectBorrowRecordById(id);
	}

	@Override
	public List<BorrowRecord> getBorrowRecordByUserId(Integer userId) {
		return recordDao.selectBorrowRecordByUserId(userId);
	}

}
