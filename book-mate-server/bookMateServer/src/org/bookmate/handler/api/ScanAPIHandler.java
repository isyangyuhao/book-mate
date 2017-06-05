package org.bookmate.handler.api;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.bookmate.entities.Admin;
import org.bookmate.entities.Borrow;
import org.bookmate.service.AdminService;
import org.bookmate.service.BorrowService;
import org.bookmate.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * 扫码借书相关接口
 * @author yangyuhao
 */
@Controller
public class ScanAPIHandler {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BorrowService borrowService;
	
	/**
	 * 管理员登录接口
	 * @param name 管理员名
	 * @param password 管理员密码
	 * @return admin 管理员实体类
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-admin-login")
	public Admin adminLogin(@RequestParam("name") String name, @RequestParam("password") String password,
			HttpSession session) {
		int loginSuccess = adminService.login(name, password);
		if (loginSuccess == 0) {
			return null;
		}
		Admin admin = adminService.getAdminByName(name);
		session.setAttribute("admin", admin);
		return admin;
	}
	
	/**
	 * 通过用户id和图书id获取借阅主键
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-borrow-book/{userId}/{bookId}")
	public int borrowBook(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId) {
		if (!borrowService.addBorrow(bookId, userId)) {
			return -1;
		}
		return borrowService.getBorrowByUserAndBookAndStatus(bookId, userId, 0).get(0).getBorrowId();
	}
	
	/**
	 * 通过id获取借阅信息接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-borrow-byid/{id}")
	public Borrow getBorrowById(@PathVariable("id") Integer id) {
		return borrowService.getBorrowById(id);
	}
	
	/**
	 * 管理员批准借书接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-allow-borrow/{id}")
	public String adminAllowBorrow(@PathVariable("id") Integer id) {
		borrowService.adminAllowBorrow(id);
		return "1";
	}
	
	/**
	 * 获取用户正在借阅的图书接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-borrowed-byuserid/{id}")
	public ArrayList<Borrow> userBorrowEd(@PathVariable("id") Integer userId) {
		//获取正在借阅的图书
		ArrayList<Borrow> borrows = (ArrayList<Borrow>) borrowService.getBorrowByUserAndStatus(userId, 1);
		ArrayList<Borrow> borrows2 = (ArrayList<Borrow>) borrowService.getBorrowByUserAndStatus(userId, 3);
		//判断当前正在借阅的书是否存在逾期未还
		long nowTime = System.currentTimeMillis();
		for (int i = 0; i < borrows.size(); ++i) {
			long startTime = borrows.get(0).getBorrowStartTime().getTime();
			int distanceDay = (int) ((nowTime - startTime) / 1000 / 60 / 60 / 24);
			if (distanceDay > 30) {
				//距离还书超过三十天,确认为逾期
				borrowService.overdueReturn(borrows.get(i).getBorrowId(), userId);
			}
		}
		//将逾期未还的图书加入显示列表汇总
		for (int i = 0; i < borrows2.size(); ++i) {
			borrows.add(borrows2.get(i));
		}
		//获取当前预订的图书
		ArrayList<Borrow> borrows3 = (ArrayList<Borrow>) borrowService.getBorrowByUserAndStatus(userId, -1);
		//判断当前正在预订的图书是否存在过期
		for (int i = 0; i < borrows3.size(); ++i) {
			long reserveTime = borrows3.get(i).getBorrowStartTime().getTime();
			int distanceDay = (int) ((nowTime - reserveTime) / 1000 / 60 / 60 / 24);
			if (distanceDay > 1) {
				//距离预订超过一天,确认为过期,删除该记录
				borrowService.removeBorrowByUserAndBookAndStatus(borrows3.get(i).getBorrowBookId(), userId, -1);
				borrows3.remove(i);
			}
		}
		//将未到期的预订图书加入显示列表汇总
		for (int i = 0; i < borrows3.size(); ++i) {
			borrows.add(borrows3.get(i));
		}
		return borrows;
	}
	
	/**
	 * 用户还书接口
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-return/{userId}/{bookId}")
	public int returnBook(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId) {
		return borrowService.returnBook(userId, bookId);
	}
	
	/**
	 * 管理员批准还书接口
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-allow-return/{id}")
	public String adminAllowReturn(@PathVariable("id") Integer id) {
		borrowService.adminAllowReturn(id);
		return "1";
	}
	
	/**
	 * 评分接口
	 * @param id
	 * @param grade
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-grade-book/{id}/{grade}")
	public String gradeBook(@PathVariable("id") Integer id, @PathVariable("grade") Integer grade) {
		borrowService.gradeBook(id, grade);
		return "1";
	}
	
	/**
	 * 获取借阅历史接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-borrow-history/{id}")
	public ArrayList<Borrow> getBorrowHistory(@PathVariable("id") Integer userId) {
		return (ArrayList<Borrow>) borrowService.getBorrowByUserAndStatus(userId, 2);
	}
	
	/**
	 * 预订图书接口
	 * @param bookId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api-scan-reserve-book/{bookId}/{userId}")
	public int reserveBook(@PathVariable("bookId") Integer bookId, @PathVariable("userId") Integer userId) {
		boolean succesFlag = borrowService.reserveBook(userId, bookId);
		if (!succesFlag) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * 删除预订图书信息
	 * @param bookId
	 * @param userId
	 */
	@ResponseBody
	@RequestMapping(value="api-book-remove-reserve/{bookId}/{userId}")
	public int removeReserveBook(@PathVariable("bookId") Integer bookId, @PathVariable("userId") Integer userId) {
		borrowService.deleteReserveBook(userId, bookId);
		return 1;
	}
	
}
