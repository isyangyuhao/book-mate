package org.bookmate.handler.admin;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.bookmate.entities.Admin;
import org.bookmate.entities.AdminMessage;
import org.bookmate.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台管理员页面控制器
 * @author yangyuhao
 */
@Controller
public class AdminHandler {
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 管理员列表显示
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-list-show", method=RequestMethod.GET)
	public String listShow(Map<String, Object> requestMap, @RequestParam("page") Integer page) {
		requestMap.put("nav", "admin-list");
		ArrayList<Admin> admins = (ArrayList<Admin>) adminService.getAllAdmin();
		requestMap.put("admins", admins);
		
		int pageCount = admins.size();  //数据条数
		int pageSize = 10;  //分页条数
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
		ArrayList<Admin> pageAdmins = new ArrayList<>();
		if (pageMax == 1) {
			pageAdmins.addAll(admins);
		} else if (pagePointer == pageMax) {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + (pageCount % pageSize); ++i) {
				pageAdmins.add(admins.get(i));
			}
		} else {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
				pageAdmins.add(admins.get(i));
			}
		}
		requestMap.put("pageMax", pageMax);
		requestMap.put("pagePoint", pagePointer);
		requestMap.put("pageAdmins", pageAdmins);
		
		return "admin/admin_list";
	}
	
	/**
	 * 搜索管理员显示
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-searchadmin-show", method=RequestMethod.GET)
	public String searchAdminShow(Map<String, Object> requestMap, @RequestParam("name") String name) {
		requestMap.put("nav", "admin-list");
		ArrayList<Admin> admins = (ArrayList<Admin>) adminService.getAdminByNameLike(name);
		requestMap.put("pageAdmins", admins);
		return "admin/admin_list";
	}
	
	/**
	 *搜索管理员通知显示
	 * @return admin/admin_message.jsp
	 */
	@RequestMapping(value="admin-admin-searchmessage-show", method=RequestMethod.GET)
	public String searchAdminMessageShow(Map<String, Object> requestMap, @RequestParam("title") String title){
		requestMap.put("nav", "admin-message");
		ArrayList<AdminMessage> adminMessages = (ArrayList<AdminMessage>) adminService.getAdminMessageByTitleLike(title);	
		requestMap.put("pageAdminMessages", adminMessages);
		return "admin/admin_message";
	}
	
	/**
	 * 管理员消息显示
	 * @return "admin/admin_message.jsp"
	 */
	@RequestMapping(value="admin-admin-message-show", method=RequestMethod.GET)
	public String messageShow(Map<String, Object> requestMap,@RequestParam("page") Integer page) {
		requestMap.put("nav", "admin-message");
		ArrayList<AdminMessage> adminMessages = (ArrayList<AdminMessage>) adminService.getAllAdminMessage();	
		requestMap.put("adminMessages", adminMessages);
		
		int pageCount = adminMessages.size();  //数据条数
		int pageSize = 10;  //分页条数
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
		ArrayList<AdminMessage> pageAdminMessages = new ArrayList<>();
		if (pageMax == 1) {
			pageAdminMessages.addAll(adminMessages);
		} else if (pagePointer == pageMax) {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + (pageCount % pageSize); ++i) {
				pageAdminMessages.add(adminMessages.get(i));
			}
		} else {
			for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
				pageAdminMessages.add(adminMessages.get(i));
			}
		}
		requestMap.put("pageMax", pageMax);
		requestMap.put("pagePoint", pagePointer);
		requestMap.put("pageAdminMessages", pageAdminMessages);
		
		return "admin/admin_message";
	}
	
	/**
	 * 新增管理员显示
	 * @return "admin/admin_register.jsp"
	 */
	@RequestMapping(value="admin-admin-register-show", method=RequestMethod.GET)
	public String registerShow() {
		return "admin/admin_register";
	}
	
	/**
	 * 新增管理员执行
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-register-execute", method=RequestMethod.POST)
	public String registerExecute(@RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("password2") String password2, 
			@RequestParam("work") String work, @RequestParam("email") String email) {
		System.out.println(name);
		boolean registerSuccess = adminService.addAdmin(name, password, password2, work, email);
		String view = "redirect:/admin-admin-list-show?page=1";
		if (!registerSuccess) {
			view = "admin/admin_register"; 
		}
		return view;
	}
	
	/**
	 * 管理员授权页面
	 * @return admin/admin_password.jsp
	 */
	@RequestMapping(value="admin-admin-password-show", method=RequestMethod.GET)
	public String passwordShow() {
		return "admin/admin_password";
	}
	
	/**
	 * 管理员授权处理
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-password-execute", method=RequestMethod.POST)
	public String passwordExecute(Map<String, Object> requestMap, HttpSession session, 
			@RequestParam String password) {
		if ("admin".equals(password)) {
			session.setAttribute("advanced", 1);
		}
		return "redirect:/admin-admin-list-show?page=1";
	}
	
	/**
	 * 删除管理员执行
	 * @return admin/admin_list.jsp
	 */
	@RequestMapping(value="admin-admin-removeadmin-execute/{id}", method=RequestMethod.GET)
	public String removeAdminExecute(@PathVariable Integer id) {
		adminService.removeAdmin(id);
		return "redirect:/admin-admin-list-show?page=1";
	}
	
	/**
	 * 修改管理员信息显示
	 * @return admin/admin_list_edit.jsp
	 */
	@RequestMapping(value="admin-admin-editadmin-show/{id}", method=RequestMethod.GET)
	public String editAdminShow(Map<String, Object> requestMap, @PathVariable Integer id) {
		Admin admin = adminService.getAdminById(id);
		requestMap.put("admin", admin);
		return "admin/admin_list_edit";
	}
	
	/**
	 * 修改管理员信息执行
	 * @return admin/admin_message.jsp
	 */
	@RequestMapping(value="admin-admin-editadmin-execute", method=RequestMethod.POST)
	public String editAdminExecute(@RequestParam("id") Integer id, 
			@RequestParam("name") String name, @RequestParam("password") String password, 
			@RequestParam("email") String email, @RequestParam("work") String work) {
		boolean editSuccess = adminService.editAdmin(id, name, password, email, work);
		String view = "redirect:/admin-admin-list-show?page=1";
		if (!editSuccess) {
			view = "redirect:/admin-admin-editadmin-show/" + id;
		}
		return view;
	}	
	
	/**
	 *添加管理员通知显示
	 * @return admin/admin_message_add.jsp
	 */
	@RequestMapping(value="admin-admin-addadminmessage-show",method=RequestMethod.GET)
	public String addAdminMessageShow(){
		return "admin/admin_message_add";
	}
	
	/**
	 *添加管理员通知执行
	 * @return admin/admin_message_add.jsp
	 */
	@RequestMapping(value="admin-admin-addadminmessage-execute",method=RequestMethod.POST)
	public String addAdminMessageExecute(@RequestParam("title") String title, 
			@RequestParam("content") String content,@RequestParam("adminId") Integer adminId){
		String view = "redirect:/admin-admin-message-show?page=1";
		boolean addSuccess = adminService.addAdminMessage(title, content, adminId);
		if (!addSuccess) {
			view = "admin/admin_message_add";
		}
		return view;
	}
	
	/**
	 * 更改管理员通知显示
	 * @param requestMap
	 * @param id
	 * @return admin/admin_message_edit.jsp
	 */
	@RequestMapping(value="admin-admin-editadminmessage-show/{id}",method=RequestMethod.GET)
	public String showAdminMessageEdit(Map<String, Object> requestMap, @PathVariable("id") Integer id){
		AdminMessage adminMessage = adminService.getAdminMessageById(id);
		requestMap.put("adminMessage", adminMessage);
		return "admin/admin_message_edit";
	}
	
	/**
	 * 更改管理员通知执行
	 * @param title
	 * @param content
	 * @param adminMessageId
	 * @return admin/admin_message.jsp
	 */
	@RequestMapping(value="admin-admin-editadminmessage-execute",method=RequestMethod.POST)
	public String editAdminMessageExecute(@RequestParam("title") String title,
			@RequestParam("content") String content,@RequestParam("adminMessageId") Integer adminMessageId){
		String view = "redirect:/admin-admin-message-show?page=1";
		boolean editSuccess = adminService.editAdminMessage(adminMessageId, title, content);
		if(!editSuccess){
			view = "admin/admin_message_edit";
		}
		return view;
	}
	
	/**
	 * 删除管理员通知
	 * @param id 管理员通知id
	 * @return admin/admin_message.jsp
	 */
	@RequestMapping(value="admin-admin-removeadminmessage-execute/{id}",method=RequestMethod.GET)
	public String removeAdminMessageExecute(@PathVariable Integer id){
		adminService.removeAdminMessage(id);
		return "redirect:/admin-admin-message-show?page=1";
	}
	
	
}
