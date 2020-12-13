package com.example.crossword;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Word.class}, version =  1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDAO wordDAO();
}
