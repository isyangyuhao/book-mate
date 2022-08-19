/*
 * ForumComment.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-13 Created
 */
package org.bookmate.entities;

import java.util.Date;

/**
 * 论坛回复实体类
 * @author yangyuhao
 */
public class ForumComment {

    //帖子回复id
    private Integer forumCommentId;
    //帖子回复内容
    private String forumCommentContent;
    //帖子回复时间
    private Date forumCommentTime;
    //帖子回复用户id
    private Integer forumCommentUserId;
    //评论对应的帖子id
    private Integer forumCommentForumId;
    
    //映射评论与用户多对一关联关系
    private User user;
    public User getUser() {
		return user;
	}
    public void setUser(User user) {
		this.user = user;
	}
    
    //映射评论与帖子多对一关联关系
    private Forum forum;
    public Forum getForum() {
		return forum;
	}
    public void setForum(Forum forum) {
		this.forum = forum;
	}

    public Integer getForumCommentId() {
        return forumCommentId;
    }
    public void setForumCommentId(Integer forumCommentId) {
        this.forumCommentId = forumCommentId;
    }
    public String getForumCommentContent() {
        return forumCommentContent;
    }
    public void setForumCommentContent(String forumCommentContent) {
        this.forumCommentContent = forumCommentContent;
    }
    public Date getForumCommentTime() {
        return forumCommentTime;
    }
    public void setForumCommentTime(Date forumCommentTime) {
        this.forumCommentTime = forumCommentTime;
    }
    public Integer getForumCommentUserId() {
        return forumCommentUserId;
    }
    public void setForumCommentUserId(Integer forumCommentUserId) {
        this.forumCommentUserId = forumCommentUserId;
    }
    public Integer getForumCommentForumId() {
		return forumCommentForumId;
	}
    public void setForumCommentForumId(Integer forumCommentForumId) {
		this.forumCommentForumId = forumCommentForumId;
	}
}