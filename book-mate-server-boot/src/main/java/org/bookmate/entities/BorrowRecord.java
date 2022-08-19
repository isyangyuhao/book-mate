/*
 * BorrowRecord.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-20 Created
 */
package org.bookmate.entities;

/**
 * 借阅记录实体类
 * @author yangyuhao
 */
public class BorrowRecord {

    //借阅记录id
    private Integer borrowRecordId;
    //借阅记录用户id
    private Integer borrowRecordUserId;
    //借阅记录类别id
    private Integer borrowRecordClassifyId;
    //借阅记录数量
    private Integer borrowRecordNumber;

    public Integer getBorrowRecordId() {
        return borrowRecordId;
    }
    public void setBorrowRecordId(Integer borrowRecordId) {
        this.borrowRecordId = borrowRecordId;
    }
    public Integer getBorrowRecordUserId() {
        return borrowRecordUserId;
    }
    public void setBorrowRecordUserId(Integer borrowRecordUserId) {
        this.borrowRecordUserId = borrowRecordUserId;
    }
    public Integer getBorrowRecordClassifyId() {
        return borrowRecordClassifyId;
    }
    public void setBorrowRecordClassifyId(Integer borrowRecordClassifyId) {
        this.borrowRecordClassifyId = borrowRecordClassifyId;
    }
    public Integer getBorrowRecordNumber() {
        return borrowRecordNumber;
    }
    public void setBorrowRecordNumber(Integer borrowRecordNumber) {
        this.borrowRecordNumber = borrowRecordNumber;
    }
}