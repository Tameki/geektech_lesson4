package com.geektech.databaselesson.data.beer;

import com.geektech.databaselesson.base.callback.BaseCallback;
import com.geektech.databaselesson.data.beer.model.BeerEntity;

import java.util.List;

// Created by askar on 10/16/18.
public interface BeerSourceContract {
    void get(final int id, final BeerCallback callback);

    void getAll(final BeerListCallback callback);

    void getAllByIds(final int[] ids, final BeerListCallback callback);

    void save(final BeerEntity... beers);

    //Абстрактные классы для того что бы не имплементировать метод onFail()
    abstract class BeerListCallback implements BaseCallback<List<BeerEntity>> {
        @Override
        public void onFail(String message) { }
    }

    abstract class BeerCallback implements BaseCallback<BeerEntity> {
        @Override
        public void onFail(String message) { }
    }
}
