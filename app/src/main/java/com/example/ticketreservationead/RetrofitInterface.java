package com.example.ticketreservationead;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitInterface {
    @POST("User/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("User")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("booking")
    Call<Void> executeReservation(@Body HashMap<String, String> map);

    @GET("User")
    Call<List<Post>> getUsers();

    @GET("trains")
    Call<List<Train>> getTrains();


//    @GET("User")
//    Call<UserData> getProfileData(@Query("userId") String userId);
@GET
Call<UserData> getProfileData(@Url String url);

    @GET
    Call<List<Reservation>>getUserReservations(@Url String url);






}

