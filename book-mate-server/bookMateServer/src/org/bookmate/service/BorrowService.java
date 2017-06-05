package org.bookmate.service;

import java.util.List;

import org.bookmate.entities.Borrow;

/**
 * 借书相关业务逻辑接口层
 * @author yangyuhao
 */
public interface BorrowService {
	
	/**
	 * 借阅图书
	 * @param bookId 图书id
	 * @param userId 用户id
	 * @return -1失败 / 插入记录的主键
	 */
	public boolean addBorrow(Integer bookId, Integer userId);
	
	/**
	 * 通过用户id,图书id,状态获取借阅信息
	 * @param bookId
	 * @param userId
	 * @param status
	 * @return borrow
	 */
	public List<Borrow> getBorrowByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status);
	
	/**
	 * 通过用户id,图书id,状态删除借阅信息
	 * @param bookId
	 * @param userId
	 * @param status
	 * @return
	 */
	public void removeBorrowByUserAndBookAndStatus(Integer bookId, Integer userId, Integer status);
	
	/**
	 * 通过用户id和状态获取借阅信息
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Borrow> getBorrowByUserAndStatus(Integer userId, Integer status);
	
	/**
	 * 通过id获取借阅信息
	 * @param id
	 * @return
	 */
	public Borrow getBorrowById(Integer id);
	
	/**
	 * 管理员批准借书
	 * @param id
	 */
	public void adminAllowBorrow(Integer id);
	
	/**
	 * 获取还书id
	 * @param userId
	 * @param bookId
	 */
	public int returnBook(Integer userId, Integer bookId);
	
	/**
	 * 管理员批准还书
	 * @param id
	 */
	public void adminAllowReturn(Integer id);
	
	/**
	 * 为借阅评分
	 * @param borrowId
	 * @param grade
	 */
	public void gradeBook(Integer borrowId, Integer grade);
	
	/**
	 * 将该记录设置为逾期未还
	 * @param borrowId
	 */
	public void overdueReturn(Integer borrowId, Integer userId);
	
	/**
	 * 获取借阅数量
	 * @return
	 */
	public Integer getBorrowCount();
	
	/**
	 * 获取还书数量
	 * @return
	 */
	public Integer getReturnCount();
	
	/**
	 * 添加虚拟借阅记录,用于充当算法结果集
	 */
	public void addShamBorrowData();
	
	public List<Borrow> getAllBorrow();
	
	/**
	 * 预订图书
	 * @param userId
	 * @param bookId
	 */
	public boolean reserveBook(Integer userId, Integer bookId);
	
	/**
	 * 删除预订信息
	 * @param userId
	 * @param bookId
	 */
	public void deleteReserveBook(Integer userId, Integer bookId);
	
}
