package org.bookmate.handler.api;

import javax.servlet.http.HttpSession;

import org.bookmate.entities.User;
import org.bookmate.service.BorrowService;
import org.bookmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

/**
 * 用户相关操作API
 * @author yangyuhao
 */
@Controller
public class UserAPIHandler {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BorrowService borrowService;
	
	/**
	 * 用户登录接口
	 * @param username 用户名
	 * @param password 密码
	 * @return 1:登录成功 0:登录失败
	 */
	@ResponseBody
	@RequestMapping(value="api-user-login")
	public User login(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		boolean loginSuccess = userService.login(username, password);
		User user = null;
		if (loginSuccess) {
			user = userService.getUserByName(username);
			session.setAttribute("user", user);
		}
		return user;
	}
	
	/**
	 * 用户注册接口
	 * @param username 用户名
	 * @param password 密码
	 * @param password2 确认密码
	 * @return 1:注册成功 0:注册失败
	 */
	@ResponseBody
	@RequestMapping(value="api-user-register")
	public String register(@RequestParam("username") String username, 
			@RequestParam("password") String password, @RequestParam("password2") String password2) {
		boolean registerSuccess = userService.register(username, password, password2);
		if (registerSuccess) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/**
	 * 退出登录接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-user-exit")
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		return "1";
	}
	
	/**
	 * 修改用户信息接口
	 * @return 1:成功 0:失败
	 */
	@ResponseBody
	@RequestMapping(value="api-user-edit")
	public String edit(@RequestParam("id") Integer id ,@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("messageStatus") Integer messageStatus, @RequestParam("newStatus") Integer newStatus,
			@RequestParam("forumStatus") Integer forumStatus, @RequestParam("recommendStatus") Integer recommendStatus) {
		if (!userService.editUser(id, username, password, messageStatus, newStatus, forumStatus, recommendStatus)) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * 获取用户已借阅数
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-user-borrow-number/{userId}")
	public Integer getBorrowNumber(@PathVariable("userId") Integer userId) {
		return borrowService.getBorrowByUserAndStatus(userId, 1).size() + borrowService.getBorrowByUserAndStatus(userId, 3).size();
	}
	
}
