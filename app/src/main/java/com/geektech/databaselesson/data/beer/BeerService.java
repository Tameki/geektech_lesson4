package com.geektech.databaselesson.data.beer;

import com.geektech.databaselesson.data.beer.model.BeerEntity;
import com.geektech.databaselesson.base.scheduler.Scheduler;

// Created by askar on 10/16/18.
// Запись в базу осуществляется с другого потока
public class BeerService implements BeerSourceContract {
    //region Static

    private static BeerService INSTANCE;

    public static BeerSourceContract getInstance(
            BeerDao dao,
            Scheduler scheduler
    ) {
        if (INSTANCE == null){
            INSTANCE = new BeerService(dao, scheduler);
        }
        return INSTANCE;
    }

    //endregion

    private BeerDao mDao;
    private Scheduler mScheduler;

    private BeerService(BeerDao dao, Scheduler scheduler) {
        this.mDao = dao;
        this.mScheduler = scheduler;
    }

    private void runOnScheduler(Runnable runnable){
        mScheduler.runOnThread(runnable);
    }

    //region Contract

    @Override
    public void get(final int id, final BeerCallback callback) {
        runOnScheduler( new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(mDao.get(id));
            }
        });
    }

    @Override
    public void getAll(final BeerListCallback callback) {
        runOnScheduler( new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(mDao.getAll());
            }
        });
    }

    @Override
    public void getAllByIds(final int[] ids, final BeerListCallback callback) {
        runOnScheduler( new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(mDao.getAllByIds(ids));
            }
        });
    }

    @Override
    public void save(final BeerEntity... beers) {
        runOnScheduler(new Runnable() {
            @Override
            public void run() {
                mDao.insertAll(beers);
            }
        });
    }

    //endregion
}
