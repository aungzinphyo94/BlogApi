package com.azp.blog.api;

import com.azp.blog.model.LoginData;
import com.azp.blog.model.RegisterData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/oauth/token")
    @FormUrlEncoded
    Call<LoginData> loginPosts(
            @Field("username") String username,
            @Field("password") String password,
            @Field("client_id") int id,
            @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type
    );

    @POST("register")
    @FormUrlEncoded
    Call<RegisterData> registerUser(
            @Field("name") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation
    );
}
