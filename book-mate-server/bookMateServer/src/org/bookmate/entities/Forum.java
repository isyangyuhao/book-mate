/*
 * Forum.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-13 Created
 */
package org.bookmate.entities;

import java.util.Date;
import java.util.List;

/**
 * 论坛帖子实体类
 * @author yangyuhao
 */
public class Forum {

    //论坛帖子id
    private Integer forumId;
    //论坛帖子标题
    private String forumTitle;
    //论坛帖子内容
    private String forumContent;
    //论坛帖子发表时间
    private Date forumTime;
    //论坛帖子发布用户id
    private Integer forumUserId;
    //论坛帖子对应书籍id
    private Integer forumBookId;

    //映射论坛和用户多对一关联关系
    private User user;
    public User getUser() {
		return user;
	}
    public void setUser(User user) {
		this.user = user;
	}
    
    //映射论坛和图书多对一关联关系
    private Book book;
    public Book getBook() {
		return book;
	}
    public void setBook(Book book) {
		this.book = book;
	}
    
    public Integer getForumId() {
        return forumId;
    }
    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }
    public String getForumTitle() {
        return forumTitle;
    }
    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }
    public String getForumContent() {
        return forumContent;
    }
    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }
    public Date getForumTime() {
        return forumTime;
    }
    public void setForumTime(Date forumTime) {
        this.forumTime = forumTime;
    }
    public Integer getForumUserId() {
        return forumUserId;
    }
    public void setForumUserId(Integer forumUserId) {
        this.forumUserId = forumUserId;
    }
    public Integer getForumBookId() {
		return forumBookId;
	}
    public void setForumBookId(Integer forumBookId) {
		this.forumBookId = forumBookId;
	}
}