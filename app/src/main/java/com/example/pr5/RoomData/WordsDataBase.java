package com.example.pr5.RoomData;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.pr5.Models.Words;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// для передачи в другие приложения базы
public abstract class WordsDataBase extends RoomDatabase  {

        public abstract WordsDao WordsDao();

        private static volatile WordsDataBase INSTANCE;
        private static final int NUMBER_OF_THREADS = 2;
        public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        public static WordsDataBase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (WordsDataBase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordsDataBase.class, "WordsDB")
                                .allowMainThreadQueries()
                                .addCallback(dataBaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
        private static final RoomDatabase.Callback dataBaseCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                databaseWriteExecutor.execute(() -> {
                    WordsDao dao = INSTANCE.WordsDao();
                    for (int i = 0; i < 10; i++) {
                        Words v = new Words("words", 1);
                        dao.insert(v);
                    }

                });
            }
        };
    }
