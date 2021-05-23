package com.violet.smon.Data.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.violet.smon.Data.Model.AuthData;

@Database(entities = {AuthData.class}, version = 1)
public abstract class AuthDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "auth_data.db";
    private static AuthDatabase instance;

    public static synchronized AuthDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AuthDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract AuthDAO authDAO();
}
