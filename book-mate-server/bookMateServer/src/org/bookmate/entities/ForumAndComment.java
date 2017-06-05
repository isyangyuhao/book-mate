package org.bookmate.entities;

import java.util.List;

/**
 * 论坛帖子和对应评论集合实体类
 * @author yangyuhao
 */
public class ForumAndComment {
	
	private Forum forum;
	private List<ForumComment> forumComments;
	private int commentNumber;
	
	public Forum getForum() {
		return forum;
	}
	public List<ForumComment> getForumComments() {
		return forumComments;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public void setForumComments(List<ForumComment> forumComments) {
		this.forumComments = forumComments;
	}
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
}
