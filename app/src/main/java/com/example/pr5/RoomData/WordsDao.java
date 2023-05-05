package com.example.pr5.RoomData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pr5.Models.Words;

import java.util.List;

// Data access object
    @Dao
    public interface WordsDao {
        Words findByName(String first, String last);
        @Insert
        void insert(Words words);
        @Update
        void update(Words words);
        @Insert
        void insertMany(Words... words);

        @Query("SELECT * FROM Words ORDER BY id")
        LiveData<List<Words>> getWords();

        @Delete
        default void delete(Words words) {
        }
    }
