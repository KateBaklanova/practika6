package com.example.pr5.Repositories;

import androidx.lifecycle.LiveData;

import com.example.pr5.DSet;
import com.example.pr5.Models.Words;
import com.example.pr5.RoomData.WordsDao;
import com.example.pr5.RoomData.WordsData;
import com.example.pr5.RoomData.WordsDataBase;

import java.util.List;

public class WordsRepositoryRoom {
    private WordsDao mWordsDao;
    public LiveData<List<Words>> words;
    WordsDataBase dataBase;

    public WordsRepositoryRoom() {
        words = WordsData.createRandomList();
        dataBase = DSet.instance.getDatabase();
        mWordsDao = DSet.instance.getDatabase().WordsDao();
    }

    public LiveData<List<Words>> getWords() {
        return words;
    }
}