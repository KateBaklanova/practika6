package com.example.pr5.UI;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.R;
import com.example.pr5.Models.Words;
import com.example.pr5.ViewModel.WordsListViewModel;
import com.example.pr5.WordsAdapter;
import com.example.pr5.databinding.Fragment2Binding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fragment2 extends Fragment{
    private ImageView AnimAndroid;
    private Fragment2Binding binding;
    private WordsListViewModel wordsListViewModel;
    private WordsAdapter wordsAdapter;

    public Fragment2() {
        super(R.layout.fragment2);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater1 = TransitionInflater.from(requireContext());
        setExitTransition(inflater1.inflateTransition(R.transition.fade
        ));

        TransitionInflater inflater2 = TransitionInflater.from(requireContext());
        setEnterTransition(inflater2.inflateTransition(R.transition.slide_right));

        wordsListViewModel = new ViewModelProvider(this).get(WordsListViewModel.class);
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = Fragment2Binding.inflate(inflater, container, false);

            binding.list.setAdapter(wordsAdapter);
            wordsListViewModel.words.observe(getViewLifecycleOwner(), wordsArrayList -> WordsAdapter.updateWordsList(wordsArrayList));

            return binding.getRoot();
        }

    static final String TAG = "working";

        public void onResume() {

            super.onResume();

            AnimAndroid = (ImageView) getActivity().findViewById(R.id.my_animated_view);

            Drawable drawable = AnimAndroid.getDrawable();
            ((Animatable) drawable).start();

            Log.e(TAG, "FDJNJNDWKSCMLJHKWDLS");

            Button button1 = (Button) getView().findViewById(R.id.button21);
            button1.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.fragment1);}
            });

            Button button = (Button) getView().findViewById(R.id.button22);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.fragment3);
                }
            });
}
}

