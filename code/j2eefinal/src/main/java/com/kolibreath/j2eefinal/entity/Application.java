package com.kolibreath.j2eefinal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//员工向经理申请类
@Entity
@Table(name = "application")
public class  Application {

    @Id
    private int applicationId;

    @Column
    private int applicantId;

    @Column
    private int handlerId;

    //需要修改的记录的Id
    @Column
    private int recordId;
}
