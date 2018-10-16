package com.geektech.databaselesson.data.beer.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.geektech.databaselesson.constants.Sql.*;

// Created by askar on 10/16/18.
@Entity(tableName = BEER_TABLE)
public class BeerEntity {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = KEY_NAME)
    private String name;

    @ColumnInfo(name = KEY_COUNTRY)
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
