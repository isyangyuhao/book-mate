package org.bookmate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.bookmate.entities.Forum;
import org.bookmate.entities.ForumComment;

import java.util.List;

/**
 * 论坛相关数据访问接口层
 * @author yangyuhao
 */
@Mapper
public interface ForumDao {

	public static final String FORUM_NAMESPACE = "org.bookmate.mapper.ForumMapper.";
	public static final String FORUM_COMMENT_NAMESPACE = "org.bookmate.mapper.ForumCommentMapper.";
	
	/**
	 * 插入新帖子
	 * @param forum 论坛帖子实体类
	 */
	public void insertForum(Forum forum);
	
	/**
	 * 查询全部论坛帖子信息以及相关关联信息
	 * @return list 实体类集合
	 */
	public List<Forum> selectAllForumAndUserAndComment();
	
	/**
	 * 根据帖子id查询对应评论集合
	 * @param forumId 帖子id
	 * @return list 实体类集合
	 */
	public List<ForumComment> selectForumCommentByForumId(Integer forumId);
	
	/**
	 * 通过帖子id查找对应帖子和发布者
	 * @param id 帖子id
	 * @return forum 帖子实体类
	 */
	public Forum selectForumById(Integer id);
	
	/**
	 * 插入新评论
	 * @param forumComment 评论实体类
	 */
	public void insertForumComment(ForumComment forumComment);
	
	/**
	 * 通过用户id查找对应帖子
	 * @param userId
	 * @return
	 */
	public List<Forum> selectForumByUserId(Integer userId);
	
	/**
	 * 通过用户id查找对应评论
	 * @param userId 用户id
	 * @return 评论集合
	 */
	public List<ForumComment> selectForumCommentByUserId(Integer userId);
	
	/**
	 * 删除帖子
	 * @param forumId 帖子id
	 */
	public void deleteForum(Integer forumId);
	
	/**
	 * 删除评论
	 * @param commentId 评论id
	 */
	public void deleteForumComment(Integer commentId);
	
	/**
	 * 查找帖子数量
	 * @return
	 */
	public Integer selectForumCount();
	
	/**
	 * 查找回复数量
	 * @return
	 */
	public Integer selectForumCommentCount();
	
}
