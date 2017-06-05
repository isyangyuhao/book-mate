package org.bookmate.test;

import org.bookmate.service.ForumService;
import org.bookmate.service.RecordService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 记录相关单元测试类
 * @author yangyuhao
 */
public class RecordTest {
	
	private ApplicationContext applicationContext = null;
	private RecordService recordService = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		recordService = applicationContext.getBean(RecordService.class);
	}
	
	/**
	 * 测试记录借阅记录
	 */
	@Test
	public void testRecordBorrow() {
		recordService.recordBorrow(1, 1);
	}
	
	/**
	 * 测试记录浏览记录
	 */
	@Test
	public void testRecordBrowse() {
		recordService.recordBrowse(1, 1);
	}
	
	/**
	 * 测试通过id获取借阅记录统计
	 */
	@Test
	public void testGetBorrowRecordById() {
		System.out.println(recordService.getBorrowRecordById(50));
	}
	
	/**
	 * 测试通过用户id获取借阅记录统计
	 */
	@Test
	public void testGetBorrowRecordByUserId() {
		System.out.println(recordService.getBorrowRecordByUserId(700).size());
	}
	
}
