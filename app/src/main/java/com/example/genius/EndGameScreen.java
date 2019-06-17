package com.example.genius;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class EndGameScreen extends AppCompatActivity {

    private int score;
    private int level;
    private Boolean cancel;
    private Button backButton;
    private Button sendButton;
    private TextView scoreLabel;
    private TextView levelLabel;
    private TextView pointsLabel;
    private TextView gameOverLabel;
    private EditText usernameInput;
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
        levelLabel = findViewById(R.id.levelLabel);
        gameOverLabel = findViewById(R.id.gameOverLabel);
        scoreLabel = findViewById(R.id.scoreOverLabel);
        pointsLabel = findViewById(R.id.pointsOverLabel);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backOverButton);
        usernameInput = findViewById(R.id.usernameInput);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DB save
                cancel = true;
                onBackPressed();
            }
        });

        startAnimations();

        //AndroidBug5497 Work around
        usernameInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onWindowFocusChanged(true);
                    }
                }, 300);
            }
        });
        endGameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWindowFocusChanged(true);
                hideKeyboard(EndGameScreen.this);
            }
        });
    }

    private void startAnimations(){
        final Animation slideIn = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in);
        sendButton.setVisibility(View.INVISIBLE);
        usernameInput.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gameOverLabel.setVisibility(View.VISIBLE);
                gameOverLabel.setAnimation(slideIn);
            }
        }, 200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scoreLabel.setVisibility(View.VISIBLE);
                scoreLabel.setAnimation(slideIn);
            }
        }, 400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pointsLabel.setText(String.format(Locale.getDefault(), "%d", score));
                pointsLabel.setVisibility(View.VISIBLE);
                pointsLabel.setAnimation(slideIn);
            }
        }, 600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                levelLabel.setText(String.format(Locale.getDefault(), "%s", (level == Game.EASY) ? "Easy" : "Hard"));
                levelLabel.setVisibility(View.VISIBLE);
                levelLabel.setAnimation(slideIn);
            }
        }, 800);

        final Animation fadeIn = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendButton.setVisibility(View.VISIBLE);
                usernameInput.setVisibility(View.VISIBLE);
                usernameInput.setAnimation(fadeIn);
                sendButton.setAnimation(fadeIn);
            }
        }, 1200);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        try {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e){
            Toast.makeText(view.getContext(), "Error on launching keyboard!", Toast.LENGTH_SHORT).show();
        }
        view.clearFocus();
    }

    @Override
    public void onBackPressed() {
        onWindowFocusChanged(true);
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
