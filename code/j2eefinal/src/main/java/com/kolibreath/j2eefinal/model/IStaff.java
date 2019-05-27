package com.kolibreath.j2eefinal.model;

import com.kolibreath.j2eefinal.entity.PunchCard;

import java.util.List;

public interface IStaff {

    boolean signInPunch();
    boolean signOutPunch();
    List<PunchCard> checkRecord();
    boolean makeApplication();
    boolean checkSalary();
}
