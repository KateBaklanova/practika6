package com.example.pr5.UI;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.Models.MultipleResource;
import com.example.pr5.Models.User;
import com.example.pr5.Models.UserList;
import com.example.pr5.PlaceholderAPI;
import com.example.pr5.R;
import com.example.pr5.Models.Words;
import com.example.pr5.RetrofitFactory;
import com.example.pr5.WordsAdapter;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.Models.Words;
import com.example.pr5.R;
import com.example.pr5.WordsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Fragment5 extends Fragment{

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater1 = TransitionInflater.from(requireContext());
        setExitTransition(inflater1.inflateTransition(R.transition.fade
        ));

        getDataFromApi ();

        TransitionInflater inflater2 = TransitionInflater.from(requireContext());
        setEnterTransition(inflater2.inflateTransition(R.transition.slide_right));
    }

    public final String URL_API = "https://reqres.in";

    private void getDataFromApi () {
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<MultipleResource> call = placeholderAPI.getPosts();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                
                if (response.isSuccessful()) {
                    String displayResponse = "";

                    MultipleResource resource = response.body();
                    Integer text = resource.page;
                    Integer total = resource.total;
                    Integer totalPages = resource.totalPages;
                    List<MultipleResource.Datum> datumList = resource.data;

                    displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                    for (MultipleResource.Datum datum : datumList) {
                        displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                    }

                    Log.d("Success", displayResponse);
                    TextView textView = getActivity().findViewById(R.id.t33);
                    textView.setText(displayResponse);
                } else {
                    Log.d("Bad", "Mistake!");
                    return;
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Ей", "Ошибка!!");
            }
        });
        Log.d("Эй","Привет!");

        User user = new User("morpheus", "leader");
        Call<User> call1 = placeholderAPI.createUser(user);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
                Toast.makeText(getContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });

        Call call2 = placeholderAPI.doCreateUserWithField("Super","Cringe");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
                Log.d("post!", "did");

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name +
                            " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
        
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