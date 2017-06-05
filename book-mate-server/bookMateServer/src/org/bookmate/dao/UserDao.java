package org.bookmate.dao;

import java.util.List;

import org.bookmate.entities.AdminMessage;
import org.bookmate.entities.User;

/**
 * 用户相关数据访问接口层
 * @author yangyuhao
 */
public interface UserDao {

	public static final String USER_NAMESPACE = "org.bookmate.mapper.UserMapper.";
	
	/**
	 * 通过用户名获取用户实体类
	 * @param username 用户名
	 * @return user 用户实体类
	 */
	public User selectUserByName(String username);
	
	/**
	 * 查询所有用户实体类
	 * @return users 用户实体类集合
	 */
	public List<User> selectAllUser();
	
	/**
	 * 插入一个新用户
	 * @param user 用户实体类
	 */
	public void insertUser(User user);
	
	/**
	 * 根据id删除一个用户
	 * @param userId 用户id
	 */
	public void deleteUser(Integer userId);
	
	/**
	 * 通过用户名模糊查询用户实体类
	 * @param username 用户名
	 * @return user 用户实体类集合
	 */
	public List<User> selectUserByUserNameLike(String username);
	
	/**
	 * 修改用户信息
	 * @param user 用户实体类
	 */
	public void updateUser(User user);
	
	/**
	 * 通过id查找对应用户实体类
	 * @param id 用户id
	 * @return user 用户实体类
	 */
	public User selectUserById(Integer id);
	
	/**
	 * 查找用户数量
	 * @return
	 */
	public Integer selectUserCount();
	
}
