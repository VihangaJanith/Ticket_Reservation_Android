package com.example.ticketreservationead;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("User/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("User")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @GET("User")
    Call<List<Post>> getUsers();


}

