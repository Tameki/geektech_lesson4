package com.geektech.databaselesson.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geektech.databaselesson.sqlite.model.Beer;

import java.util.ArrayList;
import java.util.List;

import static com.geektech.databaselesson.constants.Sql.*;

// Created by askar on 10/16/18.
public class BeerLocalDataSource extends SQLiteOpenHelper
        implements BeerDataSource {

    public BeerLocalDataSource(Context context){
        super(context,
                SQLITE_DB_FILE_NAME,
                null,
                DB_VERSION);
    }

    //region Lifecycle

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createBeerField = "CREATE TABLE " + BEER_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
                + " TEXT," + KEY_COUNTRY + " TEXT)";

        sqLiteDatabase.execSQL(createBeerField);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BEER_TABLE);
        onCreate(sqLiteDatabase);
    }

    //endregion

    //region Contract

    @Override
    public Beer getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] requiredFields = new String[]{
                KEY_ID,
                KEY_NAME,
                KEY_COUNTRY
        };
        String[] args = new String[]{String.valueOf(id)};

        Cursor cursor = db.query(
                BEER_TABLE,
                requiredFields,
                KEY_ID+"=?",
                args, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        } else {
            return null;
        }

        return Mapper.fromCursor(cursor);
    }

    @Override
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{String.valueOf(id)};

        db.delete(
                BEER_TABLE,
                KEY_ID + "=?",
                args
        );

        db.close();
    }

    @Override
    public List<Beer> getAll() {
        List<Beer> dataList = new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();

        String query="SELECT * FROM " + BEER_TABLE;

        db.rawQuery(query, null);

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                dataList.add(Mapper.fromCursor(cursor));
            }while(cursor.moveToNext());
        }
        cursor.close();

        db.close();

        return dataList;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + BEER_TABLE;
        db.execSQL(query);

        db.close();
    }

    @Override
    public void addAll(Beer... beers) {
        //Да, я говорил что так лучше не делать ;)
        for (Beer beer:beers){
            add(beer);
        }
    }

    @Override
    public void add(Beer beer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, beer.getName());
        values.put(KEY_COUNTRY, beer.getCountry());

        db.insert(
                BEER_TABLE,
                null,
                values);

        db.close();
    }

    @Override
    public void update(Beer beer) {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, beer.getName());
        values.put(KEY_COUNTRY, beer.getCountry());

        String[] args = new String[]{String.valueOf(beer.getId())};

        db.update(
                BEER_TABLE,
                values,
                KEY_ID+"=?",
                args);

        db.close();
    }

    //endregion
}
