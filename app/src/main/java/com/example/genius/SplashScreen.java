package com.example.genius;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.genius.realm.Score;

import javax.annotation.ParametersAreNonnullByDefault;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashScreen extends AppCompatActivity {

    private ImageView green;
    private ImageView yellow;
    private ImageView blue;
    private ImageView red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        findViewById(R.id.splashLinearLayout).setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        realmInit();

        assign();

        startAnimations();
    }

    private void startAnimations() {
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

    private void realmInit() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("genius.realm").build();
        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            @ParametersAreNonnullByDefault
            public void execute(Realm realm) {
                Number maxId = realm.where(Score.class).max("idScore");
                Score.id = (maxId == null) ? 1 : maxId.intValue() + 1;
            }
        });
    }

    private void assign() {
        green = findViewById(R.id.greenSplashIcon);
        yellow = findViewById(R.id.yellowSplashIcon);
        blue = findViewById(R.id.blueSplashIcon);
        red = findViewById(R.id.redSplashIcon);
    }

}
