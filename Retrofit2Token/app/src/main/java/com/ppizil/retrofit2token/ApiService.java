package com.ppizil.retrofit2token;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    // dummy url
    String URL = "https://api.example.com" + "/v1/";

    @POST("login")
    Call<Authorization> loginAccount(@Header("Authorization") String authKey);

    @GET("accounts/{accountId}")
    Call<Authorization> getAccountInfo(@Header("Authorization") String authKey,
                                     @Path("accountId") String accountId);
}