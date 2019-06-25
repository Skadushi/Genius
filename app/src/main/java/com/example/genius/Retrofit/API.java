package com.example.genius.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "https://api.myjson.com/bins/";

    @GET("10uoqh")
    Call<AboutText> getText();

}
