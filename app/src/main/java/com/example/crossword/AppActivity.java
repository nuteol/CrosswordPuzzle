package com.example.crossword;

import android.app.Application;

import androidx.room.Room;

public class AppActivity extends Application {

    static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db")
                .allowMainThreadQueries().build();
    }

    public static AppDatabase getDatabase() {
        return db;
    }
}