package com.kolibreath.j2eefinal.scheduler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StarSchedualer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        GetSchedual.getScheduler().start();
    }
}
