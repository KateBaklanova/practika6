package com.example.pr5.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class WordWorker extends Worker {

    static final String TAG = "working";

    public WordWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: start");

        try {
            TimeUnit.SECONDS.sleep(10);
            Log.e(TAG, "Таймер задержки");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "doWork: end");

        return Worker.Result.success();
    }
}