package org.bookmate.test;

import org.bookmate.service.BorrowService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 借书相关单元测试类
 * @author yangyuhao
 *
 */
public class BorrowTest {
	
	private ApplicationContext applicationContext = null;
	private BorrowService borrowService = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		borrowService = applicationContext.getBean(BorrowService.class);
	}
	
	/**
	 * 测试借书
	 */
	@Test
	public void testAddBorrow() {
		System.out.println(borrowService.addBorrow(1, 1));
		System.out.println(borrowService.getBorrowByUserAndBookAndStatus(1, 1, 0).get(0).getBorrowId());
	}
	
	/**
	 * 测试通过用户id,图书id,状态删除借阅记录
	 */
	@Test
	public void testDeleteBorrowByUserAndBookAndStatus() {
		borrowService.removeBorrowByUserAndBookAndStatus(1, 1, 0);
	}
	
	/**
	 * 测试通过用户id,状态获取借阅记录
	 */
	@Test
	public void testGetBorrowByUserAndStatus() {
		System.out.println(borrowService.getBorrowByUserAndStatus(1, 1).size());
	}
	
	/**
	 * 测试通过id获取借阅信息
	 */
	@Test
	public void testGetBorrowById() {
		System.out.println(borrowService.getBorrowById(34).getBook().getBookName());
	}
	
	/**
	 * 测试管理员批准借书
	 */
	@Test
	public void testAdminAllowBorrow() {
		borrowService.adminAllowBorrow(45);
	}
	
	/**
	 * 测试还书
	 */
	@Test
	public void testReturnBook() {
		System.out.println(borrowService.returnBook(1, 4));
	}
	
	/**
	 * 测试获取借阅数量
	 */
	@Test
	public void testGetBorrowCount() {
		System.out.println(borrowService.getBorrowCount());
	}
	
	/**
	 * 测试获取还书数量
	 */
	@Test
	public void testGetReturnCount() {
		System.out.println(borrowService.getReturnCount());
	}
	
	/**
	 * 测试获取全部图书
	 */
	@Test
	public void testGetAllBorrow() {
		System.out.println(borrowService.getAllBorrow().size());
	}
	
}
