package org.bookmate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bookmate.dao.ForumDao;
import org.bookmate.entities.Forum;
import org.bookmate.entities.ForumAndComment;
import org.bookmate.entities.ForumComment;
import org.bookmate.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * 业务逻辑实现类
 * @author yangyuhao
 */
@Service
public class ForumServiceImpl implements ForumService {

	@Autowired
	private ForumDao forumDao;
	
	@Override
	public boolean addForum(String title, String content, Integer userId, Integer bookId) {
		if ("".equals(title) || "".equals(content) || userId == null || bookId == null) {
			return false;
		}
		Forum forum = new Forum();
		forum.setForumTitle(title);
		forum.setForumContent(content);
		forum.setForumUserId(userId);
		forum.setForumBookId(bookId);
		forum.setForumTime(new Date());
		forumDao.insertForum(forum);
		return true;
	}

	@Override
	public List<Forum> getAllForum() {
		return forumDao.selectAllForumAndUserAndComment();
	}

	@Override
	public List<ForumComment> getForumCommentByForumId(Integer forumId) {
		return forumDao.selectForumCommentByForumId(forumId);
	}

	@Override
	public List<ForumAndComment> getAllForumAndComment() {
		List<ForumAndComment> forumAndComments = new ArrayList<>();
		ArrayList<Forum> forums = (ArrayList<Forum>) this.getAllForum();
		for (int i = 0; i < forums.size(); ++i) {
			ArrayList<ForumComment> comments = (ArrayList<ForumComment>) 
					this.getForumCommentByForumId(forums.get(i).getForumId());
			ForumAndComment forumAndComment = new ForumAndComment();
			forumAndComment.setForum(forums.get(i));
			forumAndComment.setForumComments(comments);
			if (comments != null) {
				forumAndComment.setCommentNumber(comments.size());
			} else {
				forumAndComment.setCommentNumber(0);
			}
			forumAndComments.add(forumAndComment);
		}
		return forumAndComments;
	}

	@Override
	public ForumAndComment getForumAndCommentByForumId(Integer forumId) {
		Forum forum = forumDao.selectForumById(forumId);
		List<ForumComment> comments = forumDao.selectForumCommentByForumId(forumId);
		ForumAndComment forumAndComment = new ForumAndComment();
		forumAndComment.setForum(forum);
		forumAndComment.setForumComments(comments);
		forumAndComment.setCommentNumber(comments.size());
		return forumAndComment;
	}

	@Override
	public boolean addForumComment(String content, Integer userId, Integer forumId) {
		if ("".equals(content)) {
			return false;
		}
		ForumComment forumComment = new ForumComment();
		forumComment.setForumCommentContent(content);
		forumComment.setForumCommentUserId(userId);
		forumComment.setForumCommentForumId(forumId);
		forumComment.setForumCommentTime(new Date());
		forumDao.insertForumComment(forumComment);
		return true;
	}

	@Override
	public List<ForumAndComment> getForumAndCommentByUserId(Integer userId) {
		ArrayList<ForumAndComment> forumAndComments = new ArrayList<>();
		ArrayList<Forum> forums = (ArrayList<Forum>) forumDao.selectForumByUserId(userId);
		for (int i = 0; i < forums.size(); ++i) {
			ArrayList<ForumComment> comments = (ArrayList<ForumComment>) 
					forumDao.selectForumCommentByForumId(forums.get(i).getForumId());
			ForumAndComment forumAndComment = new ForumAndComment();
			forumAndComment.setForum(forums.get(i));
			forumAndComment.setForumComments(comments);
			if (comments.size() > 0) {
				forumAndComment.setCommentNumber(comments.size());
			} else {
				forumAndComment.setCommentNumber(0);
			}
			forumAndComments.add(forumAndComment);
		}
		return forumAndComments;
	}

	@Override
	public List<ForumComment> getForumCommentByUserId(Integer userId) {
		return forumDao.selectForumCommentByUserId(userId);
	}

	@Override
	public boolean removeForum(Integer forumid) {
		if (forumid <= 0 || forumid == null) {
			return false;
		}
		forumDao.deleteForum(forumid);
		return true;
	}

	@Override
	public boolean removeForumComment(Integer commentId) {
		if (commentId <= 0 || commentId == null) {
			return false;
		}
		forumDao.deleteForumComment(commentId);
		return true;
	}

	@Override
	public Integer getForumCount() {
		return forumDao.selectForumCount();
	}

	@Override
	public Integer getForumCommentCount() {
		return forumDao.selectForumCommentCount();
	}
	
}
