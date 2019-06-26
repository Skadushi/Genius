package com.example.genius;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genius.Retrofit.API;
import com.example.genius.Retrofit.AboutText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import javax.annotation.ParametersAreNonnullByDefault;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class AboutScreen extends AppCompatActivity {

    private Button backButton;
    private ImageView logoImage;
    private LinearLayout aboutLayout;
    private TextView retrofitLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        
        aboutLayout = findViewById(R.id.aboutLayout);
        aboutLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        assign();
        setLogoImage();
        retrofit();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    @Override
    protected void onRestart() {
        aboutLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onRestart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        aboutLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onWindowFocusChanged(hasFocus);
    }

    private void assign() {
        backButton = findViewById(R.id.backAboutButton);
        logoImage = findViewById(R.id.logoImage);
        retrofitLabel = findViewById(R.id.retrofitLabel);
    }

    private void setLogoImage() {
        Picasso.get().load(R.drawable.githubicon).resize(75, 75).into(logoImage);
    }

    private void retrofit() {
        Gson gson = new GsonBuilder().setLenient().create();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        API api = retrofit.create(API.class);

        Call<AboutText> call = api.getText();

        call.enqueue(new Callback<AboutText>() {
            @Override
            @ParametersAreNonnullByDefault
            public void onResponse(Call<AboutText> call, Response<AboutText> response) {
                if(response.body() != null) {
                    if(Locale.getDefault().toString().equals("pt_BR")){
                        retrofitLabel.setText(response.body().getTextptbr());
                    } else {
                        retrofitLabel.setText(response.body().getText());
                    }
                }
            }

            @Override
            @ParametersAreNonnullByDefault
            public void onFailure(Call<AboutText> call, Throwable t) {
                retrofitLabel.setText(R.string.internet);
            }

        });

    }

}
