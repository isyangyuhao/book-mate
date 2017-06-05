/*
 * Borrow.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-16 Created
 */
package org.bookmate.entities;

import java.util.Date;

/**
 * 借阅信息实体类
 * @author yangyuhao
 */
public class Borrow {

    //借阅id
    private Integer borrowId;
    //借阅图书id
    private Integer borrowBookId;
    //借阅用户id
    private Integer borrowUserId;
    //借书时间
    private Date borrowStartTime;
    //还书时间
    private Date borrowEndTime;
    //借阅状态
    private Integer borrowStatus;
    //借阅评分
    private Integer borrowGrade;
    
    //映射用户与借阅一对多关联关系
    private User user;
    public User getUser() {
		return user;
	}
    public void setUser(User user) {
		this.user = user;
	}
    
    //映射图书与借阅一对多关联关系
    private Book book;
    public Book getBook() {
		return book;
	}
    public void setBook(Book book) {
		this.book = book;
	}

    public Integer getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }
    public Integer getBorrowBookId() {
        return borrowBookId;
    }
    public void setBorrowBookId(Integer borrowBookId) {
        this.borrowBookId = borrowBookId;
    }
    public Integer getBorrowUserId() {
        return borrowUserId;
    }
    public void setBorrowUserId(Integer borrowUserId) {
        this.borrowUserId = borrowUserId;
    }
    public Date getBorrowStartTime() {
        return borrowStartTime;
    }
    public void setBorrowStartTime(Date borrowStartTime) {
        this.borrowStartTime = borrowStartTime;
    }
    public Date getBorrowEndTime() {
        return borrowEndTime;
    }
    public void setBorrowEndTime(Date borrowEndTime) {
        this.borrowEndTime = borrowEndTime;
    }
    public Integer getBorrowStatus() {
        return borrowStatus;
    }
    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }
    public Integer getBorrowGrade() {
        return borrowGrade;
    }
    public void setBorrowGrade(Integer borrowGrade) {
        this.borrowGrade = borrowGrade;
    }
}