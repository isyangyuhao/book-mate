package org.bookmate.entities;

import java.util.Date;

/**
 * 管理员实体类
 * @author yangyuhao
 */
public class Admin {

    //管理员id
    private Integer adminId;
    //管理员名
    private String adminUsername;
    //管理员密码
    private String adminPassword;
    //管理员职能
    private String adminWork;
    //管理员邮箱
    private String adminEmail;
    //创建时间
    private Date adminCreateTime;

    public Integer getAdminId() {
        return adminId;
    }
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
    public String getAdminUsername() {
        return adminUsername;
    }
    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    public String getAdminWork() {
        return adminWork;
    }
    public void setAdminWork(String adminWork) {
        this.adminWork = adminWork;
    }
    public String getAdminEmail() {
        return adminEmail;
    }
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
    public Date getAdminCreateTime() {
        return adminCreateTime;
    }
    public void setAdminCreateTime(Date adminCreateTime) {
        this.adminCreateTime = adminCreateTime;
    }
}