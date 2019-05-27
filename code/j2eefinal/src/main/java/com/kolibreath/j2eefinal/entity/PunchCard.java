package com.kolibreath.j2eefinal.entity;

import javax.persistence.*;

@Entity
@Table(name= "punch_card")
public class PunchCard {

    @Id
    @GeneratedValue
    private int punchCardId;

    @Column
    private long signInStamp;

    @Column
    private long signOutStamp;

    @Column
    private String date;

    @Column
    private int staffId;

    @Column
    private int status;


    public int getPunchCardId() {
        return punchCardId;
    }

    public void setPunchCardId(int punchCardId) {
        this.punchCardId = punchCardId;
    }

    public long getSignInStamp() {
        return signInStamp;
    }

    public void setSignInStamp(long signInStamp) {
        this.signInStamp = signInStamp;
    }

    public long getSignOutStamp() {
        return signOutStamp;
    }

    public void setSignOutStamp(long signOutStamp) {
        this.signOutStamp = signOutStamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
