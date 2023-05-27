package com.example.pr5.RoomData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pr5.Models.Words;
import com.example.pr5.R;

import java.util.ArrayList;
import java.util.List;

public class WordsData {

    // информация для слов
    public static MutableLiveData<List<Words>> createRandomList() {
        MutableLiveData<List<Words>> result = new MutableLiveData<>();
        ArrayList<Words> randomWords = new ArrayList<>();
        for (int i=0; i<=200; i++){
            randomWords.add(new Words ("item"+i, R.drawable.dot));
        }
        result.setValue(randomWords);
        return result;
    }
}
