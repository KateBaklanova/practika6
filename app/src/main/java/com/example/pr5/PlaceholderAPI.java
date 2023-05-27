package com.example.pr5;
import androidx.room.Query;

import com.example.pr5.Models.MultipleResource;
import com.example.pr5.Models.User;
import com.example.pr5.Models.UserList;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlaceholderAPI {

    @GET("/api/unknown")
    Call<MultipleResource> getPosts();

    @GET("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Field("page") String page);

    @FormUrlEncoded
    @POST("/api/users")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
