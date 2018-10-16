package com.geektech.databaselesson.data.beer;

import com.geektech.databaselesson.data.beer.model.BeerEntity;

import java.util.List;

// Created by askar on 10/16/18.
public interface BeerSourceContract {
    BeerEntity get(int id);

    List<BeerEntity> getAll();

    List<BeerEntity> getAllByIds(int... ids);

    void save(BeerEntity beer);

    void saveAll(BeerEntity... beers);
}
