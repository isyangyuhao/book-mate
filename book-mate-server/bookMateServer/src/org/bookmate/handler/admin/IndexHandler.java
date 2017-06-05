package org.bookmate.handler.admin;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.bookmate.entities.Admin;
import org.bookmate.entities.AdminMessage;
import org.bookmate.service.AdminService;
import org.bookmate.service.BookService;
import org.bookmate.service.BorrowService;
import org.bookmate.service.ForumService;
import org.bookmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台首页控制器
 * @author yangyuhao
 */
@Controller
public class IndexHandler {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private ForumService forumService;
	
	/**
	 * 后台管理页面首页显示
	 * @return index/index_content.jsp
	 */
	@RequestMapping(value="admin-index-index-show", method=RequestMethod.GET)
	public String indexShow(Map<String, Object> requestMap) {
		//导航栏active
		requestMap.put("nav", "index");
		//用户数量
		requestMap.put("userCount", userService.getUserCount());
		//图书数量
		requestMap.put("bookCount", bookService.getBookCount());
		//帖子数量
		requestMap.put("forumCount", forumService.getForumCount());
		//回复数量
		requestMap.put("forumCommentCount", forumService.getForumCommentCount());
		//借阅数量
		requestMap.put("borrowCount", borrowService.getBorrowCount());
		//还书数量
		requestMap.put("returnCount", borrowService.getReturnCount());
		//管理员通知
		ArrayList<AdminMessage> adminMessages = (ArrayList<AdminMessage>) adminService.getAllAdminMessage();	
		requestMap.put("adminMessages", adminMessages);
		return "index/index_content";
	}
	
	/**
	 * 后台管理登录页面显示
	 */
	@RequestMapping(value="admin-index-login-show", method=RequestMethod.GET)
	public String loginShow() {
		return "index/index_login";
	}
	
	/**
	 * 管理员登录执行
	 * @return index/index_content.jsp
	 */
	@RequestMapping(value="admin-index-login-execute", method=RequestMethod.POST)
	public String loginExecute(Map<String, Object> requestMap, HttpSession session, 
			@RequestParam("name") String name, @RequestParam("password") String password) {
		int loginSuccess = adminService.login(name, password);
		String view = "redirect:/admin-index-index-show";
		if (loginSuccess == 1) { //登录成功
			Admin admin = adminService.getAdminByName(name);
			requestMap.put("admin", admin);
			session.setAttribute("admin", admin);
		} else { //登录失败
			requestMap.put("adminLoginError", "1");
			view = "index/index_login";
		}
		return view;
	}
	
	/**
	 * 修改账户显示
	 * @return index/index_edit.jsp
	 */
	@RequestMapping(value="admin-index-edit-show", method=RequestMethod.GET)
	public String editShow() {
		return "index/index_edit";
	}
	
	/**
	 * 修改账户信息执行
	 * @return
	 */
	@RequestMapping(value="admin-index-edit-execute", method=RequestMethod.POST)
	public String editAdminExecute(@RequestParam("id") Integer id, 
			@RequestParam("name") String name, @RequestParam("password") String password, 
			@RequestParam("email") String email, @RequestParam("work") String work, HttpSession session) {
		boolean editSuccess = adminService.editAdmin(id, name, password, email, work);
		Admin admin = adminService.getAdminById(id);
		session.setAttribute("admin", admin);
		String view = "redirect:/admin-index-index-show";
		if (!editSuccess) {
			view = "redirect:/admin-index-edit-show";
		}
		return view;
	}
	
	/**
	 * 退出登录
	 * @return index/index_login.jsp
	 */
	@RequestMapping(value="admin-index-exit-execute", method=RequestMethod.GET)
	public String exitExecute(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("advanced");
		return "redirect:/admin-index-login-show";
	}
	
	/**
	 * 显示管理员通知详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="admin-index-message-show/{id}", method=RequestMethod.GET)
	public String adminMessageShow(@PathVariable("id") Integer id, Map<String, Object> requestMap) {
		requestMap.put("nav", "index");
		AdminMessage adminMessage = adminService.getAdminMessageById(id);
		requestMap.put("adminMessage", adminMessage);
		return "index/index_message";
	}
	
}
