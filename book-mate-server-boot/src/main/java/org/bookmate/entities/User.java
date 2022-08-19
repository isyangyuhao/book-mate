/*
 * User.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-10 Created
 */
package org.bookmate.entities;

import java.util.Date;

/**
 * 用户实体类
 * @author yangyuhao
 */
public class User {

    //用户id
    private Integer userId;
    //用户名
    private String userUsername;
    //用户密码
    private String userPassword;
    //用户信用值
    private int userCredit;
    //创建时间
    private Date userCreateTime;
    //消息推送开关
    private int userMessageStatus;
    //新书推荐开关
    private int userNewStatus;
    //书友推荐开关
    private int userForumStatus;
    //个人推荐数量
    private int userRecommendStatus;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserCredit() {
		return userCredit;
	}
	public void setUserCredit(int userCredit) {
		this.userCredit = userCredit;
	}
	public Date getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	public int getUserMessageStatus() {
		return userMessageStatus;
	}
	public void setUserMessageStatus(int userMessageStatus) {
		this.userMessageStatus = userMessageStatus;
	}
	public int getUserNewStatus() {
		return userNewStatus;
	}
	public void setUserNewStatus(int userNewStatus) {
		this.userNewStatus = userNewStatus;
	}
	public int getUserForumStatus() {
		return userForumStatus;
	}
	public void setUserForumStatus(int userForumStatus) {
		this.userForumStatus = userForumStatus;
	}
	public int getUserRecommendStatus() {
		return userRecommendStatus;
	}
	public void setUserRecommendStatus(int userRecommendStatus) {
		this.userRecommendStatus = userRecommendStatus;
	}

}