package com.geektech.databaselesson.base.scheduler;

// Created by askar on 10/16/18.
public class SchedulerProvider {
    public static Scheduler getIOScheduler(){
        return IOScheduler.getInstance();
    }

    public static Scheduler getSingleThreadScheduler(){
        return SingleThreadScheduler.getInstance();
    }
}
