package com.example.genius.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "https://api.myjson.com/bins/";

    @GET("idi27")
    Call<AboutText> getText();

}
