package com.kolibreath.j2eefinal.entity;

import javax.persistence.*;

//员工向经理申请类
@Entity
@Table(name = "application")
public class  Application {

    @Id
    @GeneratedValue
    private int applicationId;

    @Column
    private int applicantId;

    @Column
    private int handlerId;

    //需要修改的记录的Id
    @Column
    private int recordId;

    @Column
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(int handlerId) {
        this.handlerId = handlerId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
