package com.example.crossword;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CrosswordDAO {

    @Insert
    void insert(Crossword crossword);

    @Query("DELETE FROM Crossword")
    void deleteAll();

    @Query("SELECT * from Crossword")
    List<Crossword> getAllCrosswords();
}
