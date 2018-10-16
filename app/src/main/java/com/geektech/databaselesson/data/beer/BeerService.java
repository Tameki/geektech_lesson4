package com.geektech.databaselesson.data.beer;

import com.geektech.databaselesson.data.beer.model.BeerEntity;

import java.util.List;

// Created by askar on 10/16/18.
public class BeerService implements BeerSourceContract {
    //region Static

    private static BeerService INSTANCE;

    public static BeerSourceContract getInstance(BeerDao dao) {
        if (INSTANCE == null){
            INSTANCE = new BeerService(dao);
        }
        return INSTANCE;
    }

    //endregion

    private BeerDao mDao;

    private BeerService(BeerDao dao) {
        this.mDao = dao;
    }

    //region Contract

    @Override
    public BeerEntity get(int id) {
        return mDao.get(id);
    }

    @Override
    public List<BeerEntity> getAll() {
        return mDao.getAll();
    }

    @Override
    public List<BeerEntity> getAllByIds(int... ids) {
        return mDao.getAllByIds(ids);
    }

    @Override
    public void save(BeerEntity beer) {
        mDao.insertAll(beer);
    }

    @Override
    public void saveAll(BeerEntity... beers) {
        mDao.insertAll(beers);
    }

    //endregion
}
