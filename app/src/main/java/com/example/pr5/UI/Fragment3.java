package com.example.pr5.UI;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

import com.example.pr5.R;
import com.example.pr5.workers.WordWorker;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Fragment3 extends Fragment {


    public Fragment3() {
        super(R.layout.fragment3);
    }

    class MyRunnable1 implements Runnable {
        @Override
        public void run() {
            TextView textView = (TextView) getView().findViewById(R.id.t33);
            textView.setText("Таймер");
        }
    }

    class MyRunnable2 implements Runnable {
        @Override
        public void run() {
            TextView textView = (TextView) getView().findViewById(R.id.timer);
            for (int i=0; i<10; ++i) {
                System.out.println(i+" fkmfpokveoefpwocf");
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                    textView.setText(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onResume() {

        super.onResume();

        OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(WordWorker.class).build();
        WorkManager.getInstance().enqueue(myWorkRequest);

        OneTimeWorkRequest myWorkRequest1 = new OneTimeWorkRequest.Builder(WordWorker.class)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new MyRunnable1());
        executorService.submit(new MyRunnable2());
        executorService.shutdown();

        Button button1 = (Button) getView().findViewById(R.id.button32);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.fragment5);
                WorkManager.getInstance().cancelWorkById(myWorkRequest.getId());
            }
        });
        Button button = (Button) getView().findViewById(R.id.button31);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.fragment2);
                WorkManager.getInstance().cancelWorkById(myWorkRequest.getId());
            }
        });
    }
}