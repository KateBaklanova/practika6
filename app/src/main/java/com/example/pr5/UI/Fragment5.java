package com.example.pr5.UI;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.R;
import com.example.pr5.Models.Words;
import com.example.pr5.WordsAdapter;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.Models.Words;
import com.example.pr5.R;
import com.example.pr5.WordsAdapter;

import java.util.ArrayList;

public class Fragment5 extends Fragment{

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater1 = TransitionInflater.from(requireContext());
        setExitTransition(inflater1.inflateTransition(R.transition.fade
        ));

        TransitionInflater inflater2 = TransitionInflater.from(requireContext());
        setEnterTransition(inflater2.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.fragment5, container, false);

        ListView listView = (ListView) contentView.findViewById(R.id.listView);

        ArrayList<String> options = new ArrayList<String>();

        options.add("Видео");
        options.add("Карточки");
        options.add("Мини игры");
        ArrayAdapter<String> adapter1 = new ArrayAdapter(contentView.getContext(), android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter1);
        return contentView;
    }
    public Fragment5() {
        super(R.layout.fragment5);
    }

    public void onResume() {
        super.onResume();
    }
}