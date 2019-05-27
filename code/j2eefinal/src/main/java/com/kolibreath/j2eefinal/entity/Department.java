package com.kolibreath.j2eefinal.entity;

import javax.persistence.*;

@Entity
@Table(name= "department")
public class Department {

    @Id
    @GeneratedValue
    private int departId;

    @Column
    private String departmentName;

    @Column
    private int siginStartTime;

    @Column
    private int signoutStartTime;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public int getSiginStartTime() {
        return siginStartTime;
    }

    public void setSiginStartTime(int siginStartTime) {
        this.siginStartTime = siginStartTime;
    }

    public int getSignoutStartTime() {
        return signoutStartTime;
    }

    public void setSignoutStartTime(int signoutStartTime) {
        this.signoutStartTime = signoutStartTime;
    }
}
