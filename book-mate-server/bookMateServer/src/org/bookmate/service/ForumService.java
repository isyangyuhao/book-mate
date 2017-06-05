package org.bookmate.service;

import java.util.List;

import org.bookmate.entities.Forum;
import org.bookmate.entities.ForumAndComment;
import org.bookmate.entities.ForumComment;

/**
 * 论坛相关业务逻辑接口层
 * @author yangyuhao
 */
public interface ForumService {

	/**
	 * 发布新论坛帖子
	 * @param title 标题
	 * @param content 内容
	 * @param userId 用户id
	 * @param bookId 图书id
	 * @return true:发布成功 false:发布失败
	 */
	public boolean addForum(String title, String content, Integer userId, Integer bookId);
	
	/**
	 * 获取全部帖子信息
	 * @return list 帖子信息实体类集合
	 */
	public List<Forum> getAllForum();
	
	/**
	 * 获取指定帖子的评论集合
	 * @return list 实体类集合
	 */
	public List<ForumComment> getForumCommentByForumId(Integer forumId);
	
	/**
	 * 获取全部帖子以及对应评论
	 * @return list 实体类集合
	 */
	public List<ForumAndComment> getAllForumAndComment();
	
	/**
	 * 根据帖子id获取帖子以及对应评论
	 * @param forumId 帖子id
	 * @return forumAndComment实体类
	 */
	public ForumAndComment getForumAndCommentByForumId(Integer forumId);
	
	/**
	 * 添加评论
	 * @param content 评论内容
	 * @param userId 用户id
	 * @param forumId 帖子id
	 * @return true:成功 false:失败
	 */
	public boolean addForumComment(String content, Integer userId, Integer forumId);
	
	/**
	 * 根据用户id获取帖子以及对应评论
	 * @param userId
	 * @return
	 */
	public List<ForumAndComment> getForumAndCommentByUserId(Integer userId);
	
	/**
	 * 根据用户id获取评论
	 * @param userId 用户id
	 * @return 评论集合
	 */
	public List<ForumComment> getForumCommentByUserId(Integer userId);
	
	/**
	 * 删除帖子
	 * @param forumid 帖子id
	 * @return true:成功 false:失败
	 */
	public boolean removeForum(Integer forumid);
	
	/**
	 * 删除评论
	 * @param commentId 评论id
	 * @return true:成功 false:失败
	 */
	public boolean removeForumComment(Integer commentId);
	
	/**
	 * 获取帖子数量
	 * @return
	 */
	public Integer getForumCount();
	
	/**
	 * 获取回复数量
	 * @return
	 */
	public Integer getForumCommentCount();
	
}
