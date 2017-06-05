package org.bookmate.dao.impl;

import java.util.List;

import org.bookmate.dao.ForumDao;
import org.bookmate.entities.Forum;
import org.bookmate.entities.ForumComment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 论坛相关数据访问实现类
 * @author yangyuhao
 */
@Repository
public class ForumDaoImpl extends SqlSessionDaoSupport implements ForumDao {
	
	@Override
	public void insertForum(Forum forum) {
		this.getSqlSession().insert(FORUM_NAMESPACE + "insertForum", forum);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> selectAllForumAndUserAndComment() {
		return this.getSqlSession().selectList(FORUM_NAMESPACE + "selectAllForumAndUser");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumComment> selectForumCommentByForumId(Integer forumId) {
		return this.getSqlSession().selectList(FORUM_COMMENT_NAMESPACE + "selectForumCommentAndUserByForumId", forumId);
	}

	@Override
	public Forum selectForumById(Integer id) {
		return (Forum) this.getSqlSession().selectOne(FORUM_NAMESPACE + "selectForumAndUserById", id);
	}

	@Override
	public void insertForumComment(ForumComment forumComment) {
		this.getSqlSession().insert(FORUM_COMMENT_NAMESPACE + "insertForumComment", forumComment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> selectForumByUserId(Integer userId) {
		return this.getSqlSession().selectList(FORUM_NAMESPACE + "selectForumAndUserByUserId", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumComment> selectForumCommentByUserId(Integer userId) {
		return this.getSqlSession().selectList(FORUM_COMMENT_NAMESPACE + "selectCommentByUserId", userId);
	}

	@Override
	public void deleteForum(Integer forumId) {
		this.getSqlSession().delete(FORUM_NAMESPACE + "deleteForum", forumId);
	}

	@Override
	public void deleteForumComment(Integer commentId) {
		this.getSqlSession().delete(FORUM_COMMENT_NAMESPACE + "deleteForumComment", commentId);
	}

	@Override
	public Integer selectForumCount() {
		return (Integer) this.getSqlSession().selectOne(FORUM_NAMESPACE + "selectForumCount");
	}

	@Override
	public Integer selectForumCommentCount() {
		return (Integer) this.getSqlSession().selectOne(FORUM_COMMENT_NAMESPACE + "selectForumCommentCount");
	}

}
