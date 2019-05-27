package com.kolibreath.j2eefinal.entity;


import javax.persistence.*;

@Entity
@Table(name ="staff")
public class Staff {

    @Id
    private int id;

    @Column
    private int staffId;

    @Column(length = 255)
    private int staffType;

    @Column(length = 255)
    private String name;


    @Column(length = 255)
    private String title;

    @Column
    private int salary;

    @Column
    private int departmentId;

    public int getStaffId() {
        return staffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getStaffType() {
        return staffType;
    }

    public void setStaffType(int staffType) {
        this.staffType = staffType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
