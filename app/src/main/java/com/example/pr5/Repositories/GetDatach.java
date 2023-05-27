package com.example.pr5.Repositories;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr5.Models.MultipleResource;
import com.example.pr5.Models.User;
import com.example.pr5.Models.UserList;
import com.example.pr5.PlaceholderAPI;
import com.example.pr5.R;
import com.example.pr5.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetDatach {

    public final String URL_API = "https://reqres.in";

    public GetDatach() {
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
                Log.d("Bad", user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt);

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
                Log.d("post!", "did");

                for (UserList.Datum datum : datumList) {
                    Log.d("Bad", "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name +
                            " avatar: " + datum.avatar);
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });

    }

}
