package com.kolibreath.j2eefinal.scheduler;

import com.kolibreath.j2eefinal.Common;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class StopPunchJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("关闭打卡系统！");
        Common.IS_PUNCH_STARTED = false;
    }
}
