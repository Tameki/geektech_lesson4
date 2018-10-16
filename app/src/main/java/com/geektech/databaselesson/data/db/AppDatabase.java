package com.geektech.databaselesson.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.geektech.databaselesson.data.beer.BeerDao;
import com.geektech.databaselesson.data.beer.model.BeerEntity;
import com.geektech.databaselesson.constants.Sql;

// Created by askar on 10/16/18.
@Database(entities = {BeerEntity.class}, version = Sql.DB_VERSION)
abstract class AppDatabase extends RoomDatabase {
    abstract BeerDao getBeerDao();
}

