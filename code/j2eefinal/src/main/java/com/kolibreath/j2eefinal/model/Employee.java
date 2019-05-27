package com.kolibreath.j2eefinal.model;

import com.kolibreath.j2eefinal.entity.PunchCard;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//普通员工
public class Employee implements IStaff {

    private int employeeId;
    PunchCardRepo repository;

    public Employee(int employeeId, PunchCardRepo repository){
        this.employeeId = employeeId;
        this.repository = repository;
    }

    @Override
    public boolean signInPunch() {
        long currentTime = System.currentTimeMillis();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(currentTime));
        PunchCard punchCard = repository.findByDateAndStaffId(currentDate,employeeId);
        if(punchCard == null) {
            punchCard = new PunchCard();
            punchCard.setDate(currentDate);
            punchCard.setSignInStamp(currentTime);
            punchCard.setSignOutStamp(0);
            punchCard.setStaffId(employeeId);
            repository.save(punchCard);
            return true;
        }
        else{
            //已经签到了
            return false;
        }
    }

    @Override
    public boolean signOutPunch() {
        return false;
    }

    @Override
    public List<PunchCard> checkRecord() {
        List<PunchCard> punchCards = repository.findByStaffId(employeeId);
        if(punchCards != null){
            if(punchCards.size() >= 3)
                punchCards = punchCards.subList(punchCards.size() - 3,punchCards.size());
            else{
                return punchCards;
            }
        }
        return punchCards;
    }

    @Override
    public boolean makeApplication() {
        return false;
    }

    @Override
    public boolean checkSalary() {
        return false;
    }
}
