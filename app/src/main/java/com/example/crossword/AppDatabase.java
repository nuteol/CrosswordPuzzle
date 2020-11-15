package com.example.crossword;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Crossword.class}, version =  1)
@TypeConverters(CharConverters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CrosswordDAO crosswordDAO();
}
