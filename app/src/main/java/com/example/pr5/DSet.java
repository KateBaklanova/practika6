package com.example.pr5;
import android.app.Application;
import com.example.pr5.Repositories.WordsRepositoryRoom;
import com.example.pr5.RoomData.WordsDataBase;

public class DSet extends Application {

    public static DSet instance;
    private WordsDataBase dataBase;
    private WordsRepositoryRoom mWordsRepos;


    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        dataBase = WordsDataBase.getDatabase(this);
        mWordsRepos = new WordsRepositoryRoom();
    }

    public WordsRepositoryRoom getWordsRepos(){
        return mWordsRepos;
    }
    public WordsDataBase getDatabase(){
        return dataBase;
    }
}
