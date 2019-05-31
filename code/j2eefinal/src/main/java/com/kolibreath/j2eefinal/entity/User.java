package com.kolibreath.j2eefinal.entity;

public class User {

    private String userName;
    private int userId;
    private int userType;

    public User(String userName, int userId, int userType){
        this.userName = userName;
        this.userId   = userId;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
