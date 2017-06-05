package org.bookmate.test;

import java.util.ArrayList;

import org.bookmate.entities.Forum;
import org.bookmate.entities.ForumAndComment;
import org.bookmate.service.ForumService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 论坛相关单元测试类
 * @author yangyuhao
 */
public class ForumTest {
	
	private ApplicationContext applicationContext = null;
	private ForumService forumService = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		forumService = applicationContext.getBean(ForumService.class);
	}
	
	/**
	 * 测试添加一条新论坛帖子
	 */
	@Test
	public void testAddForum() {
		String title = "testForumTitle2";
		String content = "testForumContent2";
		Integer userId = 1;
		Integer bookId = 1;
		forumService.addForum(title, content, userId, bookId);
	}
	
	/**
	 * 测试获取全部论坛帖子信息
	 */
	@Test
	public void testGetAllForum() {
		ArrayList<Forum> forums = (ArrayList<Forum>) forumService.getAllForum();
		System.out.println(forums.size());
		System.out.println(forums.get(0).getForumTitle());
		System.out.println(forums.get(0).getUser().getUserUsername());
	}
	
	/**
	 * 测试获取帖子对应的评论
	 */
	@Test
	public void testGetForumCommentByForumId() {
		System.out.println(forumService.getForumCommentByForumId(1).size());
		System.out.println(forumService.getForumCommentByForumId(1).get(0).getForumCommentContent());
		System.out.println(forumService.getForumCommentByForumId(1).get(0).getUser().getUserUsername());
	}
	
	/**
	 * 测试获取全部帖子和对应评论
	 */
	@Test
	public void testGetAllForumAndComment() {
		ArrayList<ForumAndComment> forumAndComments = (ArrayList<ForumAndComment>)
				forumService.getAllForumAndComment();
		System.out.println(forumAndComments.size());
		System.out.println(forumAndComments.get(0).getForum().getForumTitle());
		System.out.println(forumAndComments.get(0).getForum().getUser().getUserUsername());
		System.out.println(forumAndComments.get(0).getForum().getBook().getBookName());
	}
	
	/**
	 * 测试通过id获取帖子和对应评论
	 */
	@Test
	public void testGetForumAndCommentById() {
		System.out.print(forumService.getForumAndCommentByForumId(1).getCommentNumber());
	}
	
	/**
	 * 测试添加评论
	 */
	@Test
	public void testAddForumComment() {
		String content = "testContent";
		Integer userId = 1;
		Integer forumId = 1;
		forumService.addForumComment(content, userId, forumId);
	}
	
	/**
	 * 测试通过用户id获取帖子和对应评论
	 */
	@Test
	public void testGetForumAndCommentByUserId() {
		System.out.println(forumService.getForumAndCommentByUserId(1));
		System.out.println(forumService.getForumAndCommentByUserId(1).get(0).getCommentNumber());
	}
	
	/**
	 * 测试通过用户id获取评论
	 */
	@Test
	public void testGetForumCommentByUserId() {
		System.out.println(forumService.getForumCommentByUserId(1).size());
	}
	
	/**
	 * 测试删除帖子
	 */
	@Test
	public void testDeleteForum() {
		forumService.removeForum(8);
	}
	
	/**
	 * 测试删除回复
	 */
	@Test
	public void testDeleteForumComment() {
		forumService.removeForumComment(22);
	}
	
	/**
	 * 测试获取帖子数量
	 */
	@Test
	public void testGetForumCount() {
		System.out.println(forumService.getForumCount());
	}
	
	/**
	 * 测试获取回复数量
	 */
	@Test
	public void testGetForumCommentCount() {
		System.out.println(forumService.getForumCommentCount());
	}
	
}
