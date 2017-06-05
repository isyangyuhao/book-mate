package org.bookmate.dao;

import java.util.List;

import org.bookmate.entities.Book;
import org.bookmate.entities.BookClassifyTwo;
import org.bookmate.entities.BookRecommendResult;
import org.bookmate.entities.UserRecommendResult;

/**
 * 图书数据访问接口层
 * @author yangyuhao
 */
public interface BookDao {
	
	public final static String BOOK_NAMESPACE = "org.bookmate.mapper.BookMapper.";
	public final static String BOOK_CLASSIFY_TWO_NAMESPACE = "org.bookmate.mapper.BookClassifyTwoMapper.";
	public final static String USER_RECOMMEND_RESULT_NAMESPACE = "org.bookmate.mapper.UserRecommendResultmapper.xml.";
	public final static String BOOK_RECOMMEND_RESULT_NAMESPACE = "org.bookmate.mapper.BookRecommendResultMapper.xml.";
	
	/**
	 * 通过图书id获取图书实体类
	 * @param id 图书id
	 * @return book 图书实体类
	 */
	public Book selectBookById(Integer id);
	
	/**
	 * 插入新图书记录
	 * @param 添加新图书实体类
	 */
	public void insertBook(Book book);
	
	/**
	 * 插入新图书记录2
	 * @param book
	 */
	public void insertBook2(Book book);
	
	/**
	 * 查询所有图书的一级分类
	 * @return list 分类集合
	 */
	public List<String> selectAllClassifyOne();
	
	/**
	 * 根据图书一级分类查找对应的图书
	 * @param classifyOne 一级分类
	 * @return list 图书实体类集合
	 */
	public List<Book> selectBookByClassifyOne(String classifyOne);
	
	/**
	 * 更新图书信息
	 * @param address
	 */
	public void updateBook(Book book);
	
	/**
	 * 获取全部图书实体类
	 * @return books 实体类集合
	 */
	public List<Book> selectAllBook();
	
	/**
	 * 通过图书名模糊查询图书
	 * @param bookName 图书名
	 * @return list 图书实体类集合
	 */
	public List<Book> selectBookByNameLike(String bookName);
	
	/**
	 * 获取全部的图书二级分类
	 * @return
	 */
	public List<String> selectAllClassifyTwo();
	
	/**
	 * 插入二级分类记录
	 * @param bookClassifyTwo
	 */
	public void insertClassifyTwo(BookClassifyTwo bookClassifyTwo);
	
	/**
	 * 通过二级分类查找对应二级分类id
	 * @param classifyTwoName
	 * @return
	 */
	public Integer selectClassifyIdByBame(String classifyTwoName);
	
	/**
	 * 查找图书数量
	 * @return
	 */
	public Integer selectBookCount();
	
	/**
	 * 通过二级分类id查找二级分类实体类
	 * @param id
	 * @return
	 */
	public BookClassifyTwo selectClassifyTwoById(Integer id);
	
	/**
	 * 通过二级分类名获取图书
	 * @param bookName
	 * @return
	 */
	public List<Book> selectBookByClassifyTwoName(String classifyTwoName);
	
	/**
	 * 查询评分在前8名的热门图书
	 * @return
	 */
	public List<Book> selectHotBook();
	
	/**
	 * 通过用户id获取推荐给他的图书
	 * @param userId
	 * @return
	 */
	public UserRecommendResult selectUserRecommendResultByUser(Integer userId);
	
	/**
	 * 查找最后添加的8本图书
	 * @return
	 */
	public List<Book> selectNewBook();
	
	/**
	 * 通过图书id查找对应推荐的图书
	 * @param bookId
	 * @return
	 */
	public BookRecommendResult selectBookRecommendResultByBook(Integer bookId);
		
}
