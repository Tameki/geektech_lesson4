package com.geektech.databaselesson.base.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// Created by askar on 10/16/18.
class IOScheduler implements Scheduler {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int KEEP_ALIVE = 1;

    private static IOScheduler INSTANCE;

    public static Scheduler getInstance(){
        if (INSTANCE == null){
            INSTANCE = new IOScheduler();
        }
        return INSTANCE;
    }

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "IoScheduler #" + mCount.getAndIncrement());
        }
    };

    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<>();

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    private static final Executor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                    TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

    @Override
    public void runOnThread(Runnable runnable) {
        THREAD_POOL_EXECUTOR.execute(runnable);
    }
}
