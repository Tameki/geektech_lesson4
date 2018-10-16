package com.geektech.databaselesson.sqlite;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.geektech.databaselesson.constants.Sql;
import com.geektech.databaselesson.sqlite.model.Beer;

// Created by askar on 10/16/18.
class Mapper {
    @Nullable
    static Beer fromCursor(Cursor cursor){
        try {
            return new Beer(
                    cursor.getInt(cursor.getColumnIndex(Sql.KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(Sql.KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(Sql.KEY_COUNTRY))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
