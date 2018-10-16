package com.geektech.databaselesson.sqlite.model;

import android.support.annotation.NonNull;

// Created by askar on 10/16/18.
public class Beer {
    private int id;
    private String name;
    private String country;

    public Beer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Beer(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

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

    @NonNull
    @Override
    public String toString() {
        return id + " " + name + " " + country;
    }
}
