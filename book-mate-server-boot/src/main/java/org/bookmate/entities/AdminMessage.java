/*
 * AdminMessage.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-08 Created
 */
package org.bookmate.entities;

import java.util.Date;

/**
 * 
 * 
 * @author 
 * @version 1.0 2017-05-08
 */
public class AdminMessage {

    //管理员通知id
    private Integer adminMessageId;
    //管理员通知标题
    private String adminMessageTitle;
    //管理员通知内容
    private String adminMessageContent;
    //发布时间
    private Date adminMessageTime;
    //管理员id
    private Integer adminId;
    
    //映射多对一关联关系,发布消息的管理员实体类
    private Admin admin;
    public Admin getAdmin() {
		return admin;
	}
    public void setAdmin(Admin admin) {
		this.admin = admin;
	}

    public Integer getAdminMessageId() {
        return adminMessageId;
    }
    public void setAdminMessageId(Integer adminMessageId) {
        this.adminMessageId = adminMessageId;
    }
    public String getAdminMessageTitle() {
        return adminMessageTitle;
    }
    public void setAdminMessageTitle(String adminMessageTitle) {
        this.adminMessageTitle = adminMessageTitle;
    }
    public String getAdminMessageContent() {
        return adminMessageContent;
    }
    public void setAdminMessageContent(String adminMessageContent) {
        this.adminMessageContent = adminMessageContent;
    }
    public Date getAdminMessageTime() {
        return adminMessageTime;
    }
    public void setAdminMessageTime(Date adminMessageTime) {
        this.adminMessageTime = adminMessageTime;
    }
    public Integer getAdminId() {
        return adminId;
    }
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}