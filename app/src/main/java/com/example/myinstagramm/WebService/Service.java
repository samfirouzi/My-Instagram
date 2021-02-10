package com.example.myinstagramm.WebService;

import com.example.myinstagramm.model.PixabayPosts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
   @GET("?key=20138320-87165b0872f3fb3ab73f0db58&q=yellow+flowers&image_type=photo")
    Call<PixabayPosts> getAllPost();
   @GET("?key=20138320-87165b0872f3fb3ab73f0db58&image_type=photo")
    Call<PixabayPosts> getPostByKeyWord(@Query("q") String keyWord);
}
