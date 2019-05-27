package com.kolibreath.j2eefinal.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class GetSchedual {

    private static SchedulerFactory factory = new StdSchedulerFactory();

    public static Scheduler getScheduler(){
        try {
            return factory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
