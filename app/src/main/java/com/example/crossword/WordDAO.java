package com.example.crossword;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDAO {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM Word")
    void deleteAll();

    @Query("SELECT * FROM Word")
    List<Word> getAllWords();
}
