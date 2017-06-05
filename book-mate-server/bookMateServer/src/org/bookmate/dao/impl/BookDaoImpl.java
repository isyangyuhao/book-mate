package org.bookmate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bookmate.dao.BookDao;
import org.bookmate.entities.Book;
import org.bookmate.entities.BookClassifyTwo;
import org.bookmate.entities.BookRecommendResult;
import org.bookmate.entities.UserRecommendResult;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 图书数据访问实现类
 * @author yangyuhao
 *
 */
@Repository
public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao {
	
	@Override
	public Book selectBookById(Integer id) {
		return (Book) this.getSqlSession().selectOne(BOOK_NAMESPACE + "selectBookById", id);
	}

	@Override
	public void insertBook(Book book) {
		this.getSqlSession().insert(BOOK_NAMESPACE + "insertBook", book);
	}
	
	@Override
	public void insertBook2(Book book) {
		this.getSqlSession().insert(BOOK_NAMESPACE + "insertBook2", book);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectAllClassifyOne() {
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectAllClassifyOne");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectBookByClassifyOne(String classifyOne) {
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectBookByClassifyOne", classifyOne);
	}

	@Override
	public void updateBook(Book book) {
		this.getSqlSession().update(BOOK_NAMESPACE + "updateBook", book);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectAllBook() {
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectAllBook");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectBookByNameLike(String bookName) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("book_name", bookName);
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectBookByNameLike", queryMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectAllClassifyTwo() {
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectAllClassifyTwo");
	}

	@Override
	public void insertClassifyTwo(BookClassifyTwo bookClassifyTwo) {
		this.getSqlSession().insert(BOOK_CLASSIFY_TWO_NAMESPACE + "insertBookClassifyTwo", bookClassifyTwo);
	}

	@Override
	public Integer selectClassifyIdByBame(String classifyTwoName) {
		return (Integer) this.getSqlSession().selectOne(BOOK_CLASSIFY_TWO_NAMESPACE + "selectClassifyIdByBame", classifyTwoName);
	}

	@Override
	public Integer selectBookCount() {
		return (Integer) this.getSqlSession().selectOne(BOOK_NAMESPACE + "selectBookCount");
	}

	@Override
	public BookClassifyTwo selectClassifyTwoById(Integer id) {
		return (BookClassifyTwo) this.getSqlSession()
				.selectOne(BOOK_CLASSIFY_TWO_NAMESPACE + "selectClassifyById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectBookByClassifyTwoName(String classifyTwoName) {
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectBookByClassifyTwoName", classifyTwoName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectHotBook() {
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectHotBook");
	}

	@Override
	public UserRecommendResult selectUserRecommendResultByUser(Integer userId) {
		return (UserRecommendResult) this.getSqlSession()
				.selectOne(USER_RECOMMEND_RESULT_NAMESPACE + "selectUserRecommendResultByUser", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectNewBook() {
		return this.getSqlSession().selectList(BOOK_NAMESPACE + "selectNewBook");
	}

	@Override
	public BookRecommendResult selectBookRecommendResultByBook(Integer bookId) {
		return (BookRecommendResult) this.getSqlSession()
				.selectOne(BOOK_RECOMMEND_RESULT_NAMESPACE + "selectBookRecommendResultByBook", bookId);
	}
	
}
