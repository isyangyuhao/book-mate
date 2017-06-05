package org.bookmate.service.impl;

import java.util.Date;
import java.util.List;

import org.bookmate.dao.UserDao;
import org.bookmate.entities.User;
import org.bookmate.service.UserService;
import org.bookmate.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关业务逻辑层
 * @author yangyuhao
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean login(String username, String password) {
		if ("".equals(username) || "".equals(password)) {
			return false;
		}
		User user = userDao.selectUserByName(username);
		if (user == null) {
			return false;
		} else {
			if (user.getUserPassword().equals(EncoderUtil.EncoderByMd5(password))) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public User getUserByName(String username) {
		return userDao.selectUserByName(username);
	}

	@Override
	public boolean register(String username, String password, String password2) {
		if ("".equals(username) || "".equals(password) || "".equals(password2)) {
			return false;
		}
		if (!password.equals(password2)) {
			return false;
		}
		if (userDao.selectUserByName(username) != null) {
			return false;
		}
		User user = new User();
		user.setUserUsername(username);
		user.setUserPassword(EncoderUtil.EncoderByMd5(password));
		user.setUserCredit(80);
		user.setUserCreateTime(new Date());
		user.setUserMessageStatus(1);
		user.setUserNewStatus(1);
		user.setUserForumStatus(1);
		user.setUserRecommendStatus(60);
		userDao.insertUser(user);
		return true;
	}

	@Override
	public List<User> getAllUser() {
		return userDao.selectAllUser();
	}
	
	@Override
	public boolean removeUser(Integer id) {
		if (id <= 0 || id == null) {
			return false;
		}
		userDao.deleteUser(id);
		return true;
	}

	@Override
	public List<User> getUserByUserNameLike(String username) {
		return userDao.selectUserByUserNameLike(username);
	}

	@Override
	public boolean editUser(Integer id, String username, String password, Integer messageStatus, Integer newStatus,
			Integer forumStatus, Integer recommendStatus) {
		User user = userDao.selectUserById(id);
		if ("".equals(username) || "".equals(password)) {
			return false;
		}
		if (!password.equals(user.getUserPassword())) {
			password = EncoderUtil.EncoderByMd5(password);
			user.setUserPassword(password);
		}
		user.setUserUsername(username);
		user.setUserMessageStatus(messageStatus);
		user.setUserNewStatus(newStatus);
		user.setUserForumStatus(forumStatus);
		user.setUserRecommendStatus(recommendStatus);
		userDao.updateUser(user);
		return true;
	}

	@Override
	public Integer getUserCount() {
		return userDao.selectUserCount();
	}

	@Override
	public void resetCredit(Integer userId) {
		User user = userDao.selectUserById(userId);
		user.setUserCredit(80);
		userDao.updateUser(user);
	}	

}
