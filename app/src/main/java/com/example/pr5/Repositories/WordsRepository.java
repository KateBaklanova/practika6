package com.example.pr5.Repositories;

import androidx.lifecycle.LiveData;

import com.example.pr5.Models.Words;
import com.example.pr5.RoomData.WordsData;

import java.util.List;

public class WordsRepository {
        public LiveData<List<Words>> getRandomData(){
            return WordsData.createRandomList();
        }
    }
