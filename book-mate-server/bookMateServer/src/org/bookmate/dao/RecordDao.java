package org.bookmate.dao;

import java.util.List;

import org.bookmate.entities.BorrowRecord;
import org.bookmate.entities.BrowseRecord;

/**
 * 用户记录相关数据访问接口层
 * @author yangyuhao
 */
public interface RecordDao {
	
	public static final String BORROW_RECORD_NAMESPACE = "org.bookmate.mapper.BorrowRecordMapper.";
	public static final String BROWSE_RECORD_NAMESPACE = "org.bookmate.mapper.BrowseRecordMapper.";
	
	/**
	 * 插入一条新借阅记录
	 * @param borowRecord
	 */
	public void insertBorrowRecord(BorrowRecord borrowRecord);
	
	/**
	 * 通过用户id和类别id查找记录
	 * @param userId
	 * @param classifyId
	 * @return
	 */
	public BorrowRecord selectBorrowByUserAndClassify(Integer userId, Integer classifyId);
	
	/**
	 * 将指定id的记录数量加一
	 * @param id
	 */
	public void addBorrowNumberById(Integer id);
	
	/**
	 * 插入一条新浏览记录
	 * @param borowRecord
	 */
	public void insertBrowseRecord(BrowseRecord browseRecord);
	
	/**
	 * 通过用户id和类别id查找记录
	 * @param userId
	 * @param classifyId
	 * @return
	 */
	public BrowseRecord selectBrowseByUserAndClassify(Integer userId, Integer classifyId);
	
	/**
	 * 将指定id的记录数量加一
	 * @param id
	 */
	public void addBrowseNumberById(Integer id);
	
	/**
	 * 查询所有的浏览记录
	 * @return
	 */
	public List<BrowseRecord> selectAllBrowse();
	
	/**
	 * 更新浏览记录数
	 * @param id
	 * @param number
	 */
	public void updateBrowseNumber(Integer id, Integer number);
	
	/**
	 * 通过id获取借阅记录统计
	 * @param id
	 * @return
	 */
	public BorrowRecord selectBorrowRecordById(Integer id);
	
	/**
	 * 通过用户id获取借阅记录统计
	 * @param userId
	 * @return
	 */
	public List<BorrowRecord> selectBorrowRecordByUserId(Integer userId);
	
}
