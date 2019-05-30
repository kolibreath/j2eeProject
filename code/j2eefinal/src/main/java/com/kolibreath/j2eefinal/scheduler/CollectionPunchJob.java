package com.kolibreath.j2eefinal.scheduler;

import com.kolibreath.j2eefinal.CalUtils;
import com.kolibreath.j2eefinal.Common;
import com.kolibreath.j2eefinal.entity.PunchCard;
import com.kolibreath.j2eefinal.entity.Staff;
import com.kolibreath.j2eefinal.repo.DepartmentRepo;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CollectionPunchJob implements Job {

    private PunchCardRepo punchCardRepo;
    private StaffRepo     staffRepo;
    private DepartmentRepo departmentRepo;

    public void setPunchRepo(PunchCardRepo repo){
        this.punchCardRepo = repo;
    }

    public void setStaffRepo(StaffRepo repo){
        this.staffRepo = repo;
    }

    public void setDepartmentRepo(DepartmentRepo departmentRepo){
     this.departmentRepo = departmentRepo;
    }

    //每晚十点开始统计打卡信息
    //给员工添加迟到 早退 正常上班等等标记
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始统计打卡信息" );
    }


}
