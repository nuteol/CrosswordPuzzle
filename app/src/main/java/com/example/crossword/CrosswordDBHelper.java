package com.example.crossword;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrosswordDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "crossword_database";
    public static final String CROSSWORD_TABLE_NAME = "crossword";
    public static final String CROSSWORD_COLUMN_DATA = "data";
    public static final String CROSSWORD_COLUMN_NAME = "name";
    public static final String CROSSWORD_COLUMN_BODY = "body";
    public static final String CROSSWORD_COLUMN_ID = "id";
    public static final String CROSSWORD_COLUMN_USER_ID = "userId";
    public static final String CROSSWORD_COLUMN_JSONObject = "JSONObject";
    public static final String CROSSWORD_COLUMN_SIZE = "size";
    public static final String CROSSWORD_COLUMN_WORDS = "words";

    public CrosswordDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + CROSSWORD_TABLE_NAME + " (" +
                CROSSWORD_COLUMN_DATA + " TEXT, " +
                CROSSWORD_TABLE_NAME + " TEXT, " +
                CROSSWORD_COLUMN_BODY + " TEXT, " +
                CROSSWORD_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                CROSSWORD_COLUMN_USER_ID + " INTEGER, " +
                CROSSWORD_COLUMN_JSONObject + " Text," +
                CROSSWORD_COLUMN_SIZE + " INTEGER, " +
                CROSSWORD_COLUMN_WORDS + " TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CROSSWORD_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
