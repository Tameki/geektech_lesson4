package com.geektech.databaselesson.data.beer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.geektech.databaselesson.data.beer.model.BeerEntity;

import java.util.List;

// Created by askar on 10/16/18.
@Dao
public interface BeerDao {
    @Query("SELECT * FROM t_beers")
    List<BeerEntity> getAll();

    @Query("SELECT * FROM t_beers WHERE id IN (:ids)")
    List<BeerEntity> getAllByIds(int... ids);

    @Query("SELECT * FROM t_beers WHERE id=:id")
    BeerEntity get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(BeerEntity... models);

    @Delete
    void delete(BeerEntity model);
}
