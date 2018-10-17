package com.geektech.databaselesson.base.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Created by askar on 10/16/18.
// Class to run tasks on dedicated background thread, used for io/database work.
class SingleThreadScheduler implements Scheduler {
    private static SingleThreadScheduler INSTANCE;

    public static Scheduler getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SingleThreadScheduler();
        }
        return INSTANCE;
    }

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    private static final Executor SINGLE_THREAD_EXECUTOR =
            Executors.newSingleThreadExecutor();

    @Override
    public void runOnThread(Runnable runnable) {
        SINGLE_THREAD_EXECUTOR.execute(runnable);
    }
}
