package com.kolibreath.j2eefinal;

import com.kolibreath.j2eefinal.entity.Department;
import com.kolibreath.j2eefinal.entity.PunchCard;
import com.kolibreath.j2eefinal.entity.Staff;
import com.kolibreath.j2eefinal.repo.DepartmentRepo;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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


    //返回状态 String
    public static String getStatus(int status){
//        public static final int ON_TIME = 0;
//        public static final int ARRIVE_LATE =1 ;
//        public static final int LEAVE_EARLY =2 ;
//        public static final int WORK_LATE =3 ;
//        public static final int BUSINESS_TRIP =4 ;

        switch (status){
            case 0 : return "准点";
            case 1: return "迟到";
            case 2 : return "早退";
            case 3 : return "加班";
            case 4: return "出差";
            default: return "准点";
        }
    }


    //返回状态 int
    public static int getStatus(String status){
//        public static final int ON_TIME = 0;
//        public static final int ARRIVE_LATE =1 ;
//        public static final int LEAVE_EARLY =2 ;
//        public static final int WORK_LATE =3 ;
//        public static final int BUSINESS_TRIP =4 ;

        switch (status){
            case "准点" : return 0;
            case "迟到": return 1;
            case "早退" : return 2;
            case "加班" : return 3;
            case "出差": return 4;
            default: return 0;
        }
    }


    //计算员工月工资
    public  static void collectionPunchCard(StaffRepo staffRepo, PunchCardRepo punchCardRepo, DepartmentRepo departmentRepo){
        List<Staff> staff = staffRepo.findAll();
//        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        //给所有员工添加打卡情况
        //考虑没有签到的员工
        for(Staff s : staff){
//            PunchCard punchCard = punchCardRepo.findByDateAndStaffId(currentTime,s.getStaffId());
            List<PunchCard> punchCard = punchCardRepo.findByStaffId(s.getStaffId());
//            if(punchCard == null){
//                PunchCard pc = new PunchCard();
//                pc.setStaffId(s.getStaffId());
//                pc.setSignInStamp(0);
//                pc.setSignOutStamp(0);
//                pc.setDate(currentTime);
//                pc.setStatus(Common.ABSENSE);
//
//                punchCardRepo.save(pc);

//            }else{
//                PunchCard pc = punchCard;
               for(PunchCard card: punchCard) {
//                   pc.setStatus(CalUtils.punchCardStatus(departmentRepo.findById(s.getDepartmentId()).get(), pc));
//                   punchCardRepo.save(pc);
                   card.setStatus(CalUtils.punchCardStatus(departmentRepo.findById(s.getDepartmentId()).get(), card));
                   punchCardRepo.save(card);
//            }
               }
        }
    }
    //
}
