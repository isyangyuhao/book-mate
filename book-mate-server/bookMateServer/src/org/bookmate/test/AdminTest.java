package org.bookmate.test;

import java.util.ArrayList;
import java.util.Date;

import org.bookmate.entities.Admin;
import org.bookmate.entities.AdminMessage;
import org.bookmate.service.AdminService;
import org.bookmate.util.EncoderUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

/**
 * 管理员单元测试类
 * @author yangyuhao
 */
public class AdminTest {
	
	private ApplicationContext applicationContext = null;
	private AdminService adminService = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		adminService = applicationContext.getBean(AdminService.class);
	}
	
	/**
	 * 测试管理员登录
	 */
	@Test
	public void testLogin() {
		System.out.println(adminService.login("admin", "admin"));
	}
	
	/**
	 * 测试添加管理员
	 */
	@Test
	public void testAddAdmin() {
		String name = "admin_test";
		String password = "admin";
		String password2 = "admin";
		String work = "work is test";
		String email = "isyangyuhao@gmail.com";
		boolean flag = adminService.addAdmin(name, password, password2, work, email);
		if (flag) {
			System.out.println("add Admin Success!");
		} else {
			System.out.println("add Admin Error!");
		}
	}
	
	/**
	 * 测试获取全部管理员
	 */
	@Test
	public void testGetAllAdmin() {
		ArrayList<Admin> admins = (ArrayList<Admin>) adminService.getAllAdmin();
		System.out.println(admins.size());
	}
	
	/**
	 * 测试删除管理员
	 */
	@Test
	public void testRemoveAdmin() {
		boolean removeSuccess = adminService.removeAdmin(2);
		System.out.println(removeSuccess);
	}
	
	/**
	 * 测试根据id获取管理员
	 */
	@Test
	public void testGetAdminById() {
		Admin admin = adminService.getAdminById(1);
		System.out.println(admin);
	}
	
	/**
	 * 测试修改管理员信息
	 */
	@Test
	public void testEditAdmin() {
		String name = "ccc";
		String work = "ccc";
		String email = "ccc";
		String password = "ccc";
		adminService.editAdmin(2, name, password, email, work);
	}
	
	/**
	 * 测试获取全部管理员通知
	 */
	@Test
	public void testGetAllAdminMessage() {
		ArrayList<AdminMessage> adminMessages = (ArrayList<AdminMessage>) adminService.getAllAdminMessage();
		System.out.println(adminMessages.size());
		System.out.println(adminMessages.get(0).getAdminMessageTitle());
		System.out.println(adminMessages.get(0).getAdmin().getAdminUsername());
	}
	
	/**
	 * 测试添加管理员通知
	 */
	@Test
	public void testAddAdminMessage(){
		String title = "adminMessageTitle";
		String content = "adminMessageContent";
		Integer adminId = 1;
		
		boolean flag = adminService.addAdminMessage(title, content, adminId);
		if (flag) {
			System.out.println("add AdminMessage Success!");
		} else {
			System.out.println("add AdminMessage Error!");
		}
		
		
	}
	
	/**
	 * 测试更改管理员通知
	 */
	@Test
	public void testUpdateAdminMessage(){
		String title = "adminMessageTitle";
		String content = "adminMessageContent";
		Integer id = 1;
		boolean flag = adminService.editAdminMessage(id, title, content);
		if (flag) {
			System.out.println("update AdminMessage Success!");
		} else {
			System.out.println("update AdminMessage Error!");
		}
	}
	
	/**
	 * 测试删除管理员通知
	 */
	@Test
	public void testDeleteAdminMessage(){
		boolean flag = adminService.removeAdminMessage(1);
		if (flag) {
			System.out.println("delete AdminMessage Success!");
		} else {
			System.out.println("delete AdminMessage Error!");
		}
	}
	
	/**
	 * 测试通过管理员名模糊查询管理员实体类集合
	 */
	@Test
	public void testGetAdminByNameLike() {
		String name1 = "admin";
		String name2 = "admin2";
		String name3 = "";
		String name4 = "xxxxx";
		System.out.println(adminService.getAdminByNameLike(name1).size());
		System.out.println(adminService.getAdminByNameLike(name2).size());
		System.out.println(adminService.getAdminByNameLike(name3).size());
		System.out.println(adminService.getAdminByNameLike(name4).size());
	}
	
}
