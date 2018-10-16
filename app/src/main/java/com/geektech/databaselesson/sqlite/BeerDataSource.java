package com.geektech.databaselesson.sqlite;

import com.geektech.databaselesson.sqlite.model.Beer;

import java.util.List;

// Created by askar on 10/16/18.
public interface BeerDataSource {
    Beer getById(int id);

    void delete(int id);

    List<Beer> getAll();

    void deleteAll();

    void addAll(Beer... beers);

    void add(Beer beer);

    void update(Beer beer);
}
