package org.bookmate.handler.admin;

import java.util.ArrayList;
import java.util.Map;

import org.bookmate.entities.AdminMessage;
import org.bookmate.entities.User;
import org.bookmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户管理控制器
 * @author yangyuhao
 */
@Controller
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户列表显示
	 * @return user/user_list.jsp
	 */
	@RequestMapping(value="admin-user-list-show", method=RequestMethod.GET)
	public String listShow(Map<String, Object> requestMap,@RequestParam("page") Integer page) {
		requestMap.put("nav", "user");
		ArrayList<User> users = (ArrayList<User>) userService.getAllUser();
		requestMap.put("users", users);
		
		int pageCount = users.size();  //数据条数
		int pageSize = 20;  //分页条数
		int pageMax = pageCount / pageSize;  //最大页数
		int pagePointer = 1;  //当前指向页
		if (pageMax != 0 && pageCount % pageSize != 0) {
			++pageMax;
		}
		if (pageMax == 0) {
			pageMax = 1;
		}
		if (page < 1 || page > pageMax) {
			pagePointer = 1;
		} else {
			pagePointer = page;
		}
		ArrayList<User> pageUsers = new ArrayList<>();
		if (pageMax == 1) {
			pageUsers.addAll(users);
		} else if (pagePointer == pageMax) {
			int tmp = pageCount % pageSize;
			if (tmp == 0) {
				tmp = pageSize;
			}
			for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + tmp; ++i) {
				pageUsers.add(users.get(i));
			}
		} else {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
				pageUsers.add(users.get(i));
			}
		}
		requestMap.put("pageMax", pageMax);
		requestMap.put("pagePoint", pagePointer);
		requestMap.put("pageUsers",pageUsers);
		
		return "user/user_list";
	}
	
	/**
	 * 根据id删除用户执行
	 * @param id 用户id
	 * @return user_list.jsp
	 */
	@RequestMapping(value="admin-admin-removeuser-execute/{id}",method=RequestMethod.GET)
	public String removeUserExecute(@PathVariable Integer id){
		userService.removeUser(id);
		return "redirect:/admin-user-list-show?page=1";
	}
	
	/**
	 * 搜索用户显示
	 * @return user/user_list.jsp
	 */
	@RequestMapping(value="admin-user-searchuser-show",method=RequestMethod.GET)
	public String searchUserShow(Map<String, Object> requestMap,@RequestParam("username") String username){
		requestMap.put("nav", "user_list");
		ArrayList<User> users = (ArrayList<User>) userService.getUserByUserNameLike(username);
		requestMap.put("pageUsers", users);
		return "user/user_list";
	}
	
	@RequestMapping(value="admin-user-resetcredit-execute/{id}", method=RequestMethod.GET)
	public String resetCredit(@PathVariable("id") Integer id) {
		userService.resetCredit(id);
		return "redirect:/admin-user-list-show?page=1";
	}
	
	
}
