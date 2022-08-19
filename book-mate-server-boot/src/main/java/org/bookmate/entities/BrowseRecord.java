/*
 * BrowseRecord.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-20 Created
 */
package org.bookmate.entities;

/**
 * 浏览记录实体类
 * @author yangyuhao
 */
public class BrowseRecord {

    //浏览记录id
    private Integer browseRecordId;
    //浏览记录用户id
    private Integer browseRecordUserId;
    //浏览记录类别id
    private Integer browseRecordClassifyId;
    //浏览记录类别数量
    private Integer browseRecordNumber;

    public Integer getBrowseRecordId() {
        return browseRecordId;
    }
    public void setBrowseRecordId(Integer browseRecordId) {
        this.browseRecordId = browseRecordId;
    }
    public Integer getBrowseRecordUserId() {
        return browseRecordUserId;
    }
    public void setBrowseRecordUserId(Integer browseRecordUserId) {
        this.browseRecordUserId = browseRecordUserId;
    }
    public Integer getBrowseRecordClassifyId() {
        return browseRecordClassifyId;
    }
    public void setBrowseRecordClassifyId(Integer browseRecordClassifyId) {
        this.browseRecordClassifyId = browseRecordClassifyId;
    }
    public Integer getBrowseRecordNumber() {
        return browseRecordNumber;
    }
    public void setBrowseRecordNumber(Integer browseRecordNumber) {
        this.browseRecordNumber = browseRecordNumber;
    }
}