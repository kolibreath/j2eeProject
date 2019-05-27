package com.kolibreath.j2eefinal;

import com.kolibreath.j2eefinal.entity.Department;
import com.kolibreath.j2eefinal.entity.PunchCard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CalUtils {

    public static String converter(long timeMillis){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date(timeMillis);
        return format.format(date);
    }

    //计算员工今天上班情况
    public static int punchCardStatus(Department department, PunchCard punchCard){
        if(punchCard.getSignInStamp() == 0 || punchCard.getSignOutStamp() == 0)
            return Common.ABSENSE;

        long punchCardSignInStamp = punchCard.getSignInStamp();
        long punchCardSignOutStamp = punchCard.getSignOutStamp();

        //只要迟到时间在一小时以内都不算迟到
        //早退时间在一小时以内都不算早退
        int punchSignIn = Integer.parseInt(converter(punchCardSignInStamp).split("-")[3]);
        int punchSignOut = Integer.parseInt(converter(punchCardSignOutStamp).split("-")[3]);

        if (punchSignIn > department.getSiginStartTime()){
            return Common.ARRIVE_LATE;
        }else if(punchSignOut < department.getSignoutStartTime()){
            return Common.LEAVE_EARLY;
        }else if(punchSignIn > department.getSiginStartTime() && punchSignOut < department.getSignoutStartTime()){
            return Common.ABSENSE;
        }else{
            return Common.ON_TIME;
        }
    }

    //模拟员工工资
    public static int randomSalary(){
        int salary[] = new int[]{6000,8000,10000,5000,7000,8500,12000};
        return salary[(int)(System.currentTimeMillis()) % salary.length];
    }

    //模拟员工类型
    public static int randomType(){
        int[] types = {Common.EMPLOYEE, Common.MANAGER};
        return types[new Random().nextInt() % types.length];
    }


    //模拟员工头衔
    public static String randomTitle(int departmentId){
        String devTitles[] ={"高级开发者","中级开发者","初级开发者","架构师"};
        String designTitles[] = {"UI高级设计师","UI中级设计师","创意总监"};
        String mgTitles[] = {"项目组长","产品经理","项目总监","产品总监"};

        String titles[][]= {devTitles,designTitles,mgTitles};
        int i = new Random().nextInt() % titles.length;
        int j = new Random().nextInt() % titles[i].length;

        return titles[i][j];
    }
    //计算员工月工资

    //
}
