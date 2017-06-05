package org.bookmate.service;

import java.util.List;

import org.bookmate.entities.BorrowRecord;

/**
 * 记录相关业务逻辑接口层
 * @author yangyuhao
 */
public interface RecordService {
	
	/**
	 * 记录用户借阅信息
	 * @param userId 用户id
	 * @param bookId 图书id
	 */
	public void recordBorrow(Integer userId, Integer bookId);
	
	/**
	 * 记录用户浏览信息
	 * @param userId 用户id
	 * @param bookId 图书id
	 */
	public void recordBrowse(Integer userId, Integer bookId);
	
	/**
	 * 添加虚拟借阅记录，用于充当算法数据集
	 */
	public void addShamBorrowData();
	
	/**
	 * 将从虚拟借阅记录赋值过来的浏览记录中的浏览条数进行重新随机
	 */
	public void updateShamBrowseData();

	/**
	 * 添加虚拟浏览记录，用于充当算法数据集
	 */
	void addShamBrowseData();
	
	/**
	 * 通过id获取借阅记录统计
	 * @param id
	 * @return
	 */
	public BorrowRecord getBorrowRecordById(Integer id);
	
	/**
	 * 通过用户id获取借阅记录统计
	 * @param userId
	 * @return
	 */
	public List<BorrowRecord> getBorrowRecordByUserId(Integer userId);
	
}
