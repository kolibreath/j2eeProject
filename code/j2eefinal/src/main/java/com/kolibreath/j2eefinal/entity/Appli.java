package com.kolibreath.j2eefinal.entity;

import javax.persistence.*;

//员工向经理申请类
@Entity
@Table(name = "application")
public class Appli {

    @Id
    @GeneratedValue
    private long appliId;

    @Column
    private int applicantId;

    @Column
    private int handlerId;

    //需要修改的记录的Id
    @Column
    private int recordId;

    @Column
    private String reason;

    @Column
    private boolean handled;


    public boolean getHandled(){
        return handled;
    }
    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getAppliId() {
        return appliId;
    }

    public void setAppliId(int appliId) {
        this.appliId = appliId;
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
