package com.example.genius;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashScreen extends AppCompatActivity {

    private ImageView green;
    private ImageView yellow;
    private ImageView blue;
    private ImageView red;
    private LinearLayout splashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        splashLayout = findViewById(R.id.splashLinearLayout);
        splashLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("genius.realm").build();
        Realm.setDefaultConfiguration(config);

        green = findViewById(R.id.greenSplashIcon);
        yellow = findViewById(R.id.yellowSplashIcon);
        blue = findViewById(R.id.blueSplashIcon);
        red = findViewById(R.id.redSplashIcon);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { green.setVisibility(View.VISIBLE);
            }
        }, 250);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { yellow.setVisibility(View.VISIBLE);
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                blue.setVisibility(View.VISIBLE);
            }
        }, 750);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                red.setVisibility(View.VISIBLE);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent swap = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(swap);
                finish();
            }
        }, 1500);
    }

}
