package com.example.genius;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class EndGameScreen extends AppCompatActivity {

    private int score;
    private int level;
    private Boolean cancel;
    private TextView gameOver;
    private TextView scoreLabel;
    private TextView pointsLabel;
    private LinearLayout endGameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_screen);

        getSupportActionBar().hide();
        endGameLayout = findViewById(R.id.endGameLayout);
        endGameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        cancel = false;
        score = getIntent().getIntExtra("points", 0);
        level = getIntent().getIntExtra("level", Game.EASY);
        gameOver = findViewById(R.id.gameOverLabel);
        scoreLabel = findViewById(R.id.scoreOverLabel);
        pointsLabel = findViewById(R.id.pointsOverLabel);

        startAnimations();

    }

    private void startAnimations(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gameOver.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in);
                gameOver.setAnimation(animation);
            }
        }, 200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scoreLabel.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in);
                scoreLabel.setAnimation(animation);
            }
        }, 400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pointsLabel.setText(String.format(Locale.getDefault(), "%d", score));
                pointsLabel.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in);
                pointsLabel.setAnimation(animation);
            }
        }, 600);



    }

    @Override
    public void onBackPressed() {
        if(!cancel){
            Toast.makeText(this, "Click once more to go to Home Screen!", Toast.LENGTH_SHORT).show();
            cancel = true;
        } else {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        }
    }

    @Override
    protected void onRestart() {
        cancel = false;
        endGameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onRestart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        endGameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onWindowFocusChanged(hasFocus);
    }
}
