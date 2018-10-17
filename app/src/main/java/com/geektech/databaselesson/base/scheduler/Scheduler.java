package com.geektech.databaselesson.base.scheduler;

// Created by askar on 10/16/18.
// Интерфейса для асинхронного выполнения задач
public interface Scheduler {
    void runOnThread(Runnable runnable);
}
