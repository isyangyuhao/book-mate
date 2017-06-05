package org.bookmate.dao;

import java.util.ArrayList;
import java.util.List;

import org.bookmate.entities.Admin;
import org.bookmate.entities.AdminMessage;

/**
 * 管理员数据访问接口层
 * @author yangyuhao
 */
public interface AdminDao {
	
	public static final String ADMIN_NAMESPACE = "org.bookmate.mapper.AdminMapper.";
	public static final String ADMIN_MESSAGE_NAMESPACE = "org.bookmate.mapper.AdminMessageMapper.";
	
	/**
	 * 通过名称查询管理员实体类
	 * @param name 管理员名
	 * @return admin 管理员实体类
	 */
	public Admin selectAdminByUsername(String name);
	
	/**
	 * 插入管理员
	 * @param admin 管理员实体类
	 */
	public void insertAdmin(Admin admin);
	
	/**
	 * 查询所有的管理员实体类
	 * @return admins 管理员实体类集合
	 */
	public List<Admin> selectAllAdmin(); 
	
	/**
	 * 根据id删除对应管理员
	 * @param id 管理员id
	 */
	public void deleteAdmin(Integer id);
	
	/**
	 * 根据id查询管理员实体类
	 * @param id
	 * @return
	 */
	public Admin selectAdminById(Integer id);
	
	/**
	 * 更新管理员信息
	 * @param admin 管理员实体类
	 */
	public void updateAdmin(Admin admin);
	
	/**
	 * 获取全部的管理员通知
	 * @return adminMessages 管理员通知类集合
	 */
	public List<AdminMessage> selectAllAdminMessage();
	
	/**
	 * 添加管理员通知
	 * @param adminMessage 管理员通知
	 */
	public void insertAdminMessage(AdminMessage adminMessage);
	
	/**
	 * 更改管理员通知
	 * @param adminMessage 管理员通知
	 */
	public void updateAdminMessage(AdminMessage adminMessage);
	
	/**
	 * 获取管理员通知
	 * @param adminMessageId 管理员通知id
	 * @return
	 */
	public AdminMessage getAdminMessage(Integer adminMessageId);
	
	/**
	 * 删除管理员通知
	 * @param adminMessageId 管理员通知id
	 */
	public void deleteAdminMessage(Integer adminMessageId);
	
	/**
	 * 通过名称模糊查询管理员实体类
	 * @param name 管理员名
	 * @return admins 管理员实体类结合
	 */
	public List<Admin> selectAdminByUsernameLike(String name);
	
	/**
	 * 通过通知名称模糊查询管理员通知实体类
	 * @param title 管理员通知标题
	 * @return adminMessages 管理员通知实体类集合
	 */
	public List<AdminMessage> selectAdminMessageByTitleLike(String title);
	
}
