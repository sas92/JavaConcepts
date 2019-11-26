package com.sas.test;

import com.sas.runnables.CleaningScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduler {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // schedule
        //scheduledExecutorService.schedule(new CleaningScheduler(), 2, TimeUnit.SECONDS);

        // scheduleAtFixedRate
        //scheduledExecutorService.scheduleAtFixedRate(new CleaningScheduler(), 2, 5, TimeUnit.SECONDS);

        // scheduleWithFixedDelay
        scheduledExecutorService.scheduleWithFixedDelay(new CleaningScheduler(), 2, 5, TimeUnit.SECONDS);
    }
}
