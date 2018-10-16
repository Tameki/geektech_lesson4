package com.geektech.databaselesson.data;

import android.content.Context;

import com.geektech.databaselesson.data.beer.BeerService;
import com.geektech.databaselesson.data.beer.BeerSourceContract;
import com.geektech.databaselesson.data.db.DatabaseContract;
import com.geektech.databaselesson.data.db.DatabaseLessonDb;

// Created by askar on 10/16/18.
public class SourceProvider {

    private static DatabaseContract getDatabase(Context context){
        return DatabaseLessonDb.getInstance(context);
    }

    public static BeerSourceContract getBeerSource(Context context){
        return BeerService.getInstance(
                getDatabase(context).getBeerDao()
        );
    }

}
