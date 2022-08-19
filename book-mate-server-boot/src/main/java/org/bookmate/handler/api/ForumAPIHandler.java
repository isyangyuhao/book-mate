package org.bookmate.handler.api;

import org.bookmate.entities.ForumAndComment;
import org.bookmate.entities.ForumComment;
import org.bookmate.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * 论坛相关接口
 * @author yangyuhao
 */
@Controller
public class ForumAPIHandler {
	
	@Autowired
	ForumService forumService;
	
	/**
	 * 添加论坛帖子接口
	 * @param title 标题
	 * @param content 内容
	 * @param userId 用户id
	 * @param bookId 图书id
	 * @return 1:成功 0:失败
	 */
	@ResponseBody
	@RequestMapping(value="api-forum-add-forum")
	public String addForum(@RequestParam("title")String title, @RequestParam("content") String content,
			@RequestParam("userId") Integer userId, @RequestParam("bookId") Integer bookId) {
		boolean addSuccess = forumService.addForum(title, content, userId, bookId);
		if (!addSuccess) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * 获取全部论坛信息接口
	 * @return 论坛信息
	 */
	@ResponseBody
	@RequestMapping(value="api-forum-all")
	public ArrayList<ForumAndComment> getAllForumAndComment() {
		return (ArrayList<ForumAndComment>) forumService.getAllForumAndComment();
	}
	
	/**
	 * 通过id获取帖子和评论信息接口
	 * @return 论坛信息
	 */
	@ResponseBody
	@RequestMapping(value="api-forum-byid/{id}")
	public ForumAndComment getForumAndCommentById(@PathVariable("id") Integer id) {
		return forumService.getForumAndCommentByForumId(id);
	}
	
	/**
	 * 添加评论接口
	 * @return 1:成功 0:失败
	 */
	@ResponseBody
	@RequestMapping(value="api-forum-add-comment")
	public String addComment(@RequestParam("content") String content,
			@RequestParam("userId") Integer userId, @RequestParam("forumId") Integer forumId) {
		boolean addSuccess = forumService.addForumComment(content, userId, forumId);
		if (!addSuccess) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * 通过用户id获取对应帖子信息接口
	 * @param userId 用户id
	 * @return 帖子信息集合
	 */
	@ResponseBody
	@RequestMapping(value="api-forum-byuserid/{id}")
	public ArrayList<ForumAndComment> getForumAndCommentByUserId(@PathVariable("id") Integer userId) {
		return (ArrayList<ForumAndComment>) forumService.getForumAndCommentByUserId(userId);
	}
	
	/**
	 * 通过用户id获取对应评论接口
	 * @param id 用户id
	 * @return 评论集合
	 */
	@ResponseBody
	@RequestMapping(value="api-forum-comment-byuserid/{id}")
	public ArrayList<ForumComment> getForumCommentByUserId(@PathVariable("id") Integer userId) {
		return (ArrayList<ForumComment>) forumService.getForumCommentByUserId(userId);
	}
	
	/**
	 * 删除帖子接口
	 * @param forumId 帖子id
	 * @return 1:成功 0:失败
	 */
	@ResponseBody
	@RequestMapping(value="api-forum-remove-forum/{id}")
	public String removeForum(@PathVariable("id") Integer forumId) {
		if (!forumService.removeForum(forumId)) {
			return "0";
		}
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="api-forum-remove-comment/{id}")
	public String removeForumComment(@PathVariable("id") Integer commentId) {
		if (!forumService.removeForumComment(commentId)) {
			return "0";
		}
		return "1";
	}
	
}
