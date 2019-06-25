package com.example.genius.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "https://github.com/Skadushi/Genius/blob/master/";

    @GET("text")
    Call<AboutText> getText();

}
