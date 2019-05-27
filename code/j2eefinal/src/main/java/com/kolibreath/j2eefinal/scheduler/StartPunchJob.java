package com.kolibreath.j2eefinal.scheduler;

import com.kolibreath.j2eefinal.Common;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class StartPunchJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始打卡！");
        Common.IS_PUNCH_STARTED = true;
    }
}

