package com.geektech.databaselesson.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.geektech.databaselesson.data.beer.BeerDao;
import com.geektech.databaselesson.constants.Sql;

public class DatabaseLessonDb implements DatabaseContract {

    //region Static

    private static DatabaseLessonDb INSTANCE;

    public static DatabaseContract getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new DatabaseLessonDb(context);
        }
        return INSTANCE;
    }

    //endregion

    private AppDatabase db;

    private DatabaseLessonDb(Context context) {
        db = Room.databaseBuilder(
                context,
                AppDatabase.class,
                Sql.ROOM_DB_FILE_NAME).build();
    }

    @Override
    public BeerDao getBeerDao() {
        return db.getBeerDao();
    }
}
