package com.example.genius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GameScreen extends AppCompatActivity {

    public static Boolean clickable;
    private int level;
    private Boolean cancel;
    private Integer points;
    private LinearLayout gameLayout;
    private Button backButton;
    private Button startButton;
    private Button easyButton;
    private Button hardButton;
    private TextView scoreLabel;
    private TextView pointsLabel;
    private TextView numberTipLabel;
    private ImageView greenButton;
    private ImageView redButton;
    private ImageView yellowButton;
    private ImageView blueButton;
    private LinearLayout coloredLayout;

    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        clickable = false;
        cancel = true;
        points = 0;

        getSupportActionBar().hide();
        gameLayout = findViewById(R.id.gameLinearLayout);
        gameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        backButton = findViewById(R.id.backButton);
        easyButton = findViewById(R.id.easyButton);
        hardButton = findViewById(R.id.hardButton);
        startButton = findViewById(R.id.startButton);
        scoreLabel = findViewById(R.id.scoreLabel);
        pointsLabel = findViewById(R.id.pointsLabel);
        numberTipLabel = findViewById(R.id.numberTip);
        greenButton = findViewById(R.id.greenButton);
        redButton = findViewById(R.id.redButton);
        yellowButton = findViewById(R.id.yellowButton);
        blueButton = findViewById(R.id.blueButton);
        coloredLayout = findViewById(R.id.coloredLayout);

        gameEngine = new GameEngine();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel = false;
                startButton.setVisibility(View.GONE);
                numberTipLabel.setVisibility(View.VISIBLE);
                gameEngine.showColors(coloredLayout, numberTipLabel);
                clickable = false;
            }
        });

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel = false;
                level = Game.EASY;
                pointsLabel.setText(String.format(Locale.getDefault(), "%d", points));
                gameEngine.setDifficulty(Game.EASY_SPEED);
                scoreLabel.setVisibility(View.VISIBLE);
                pointsLabel.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
                easyButton.setVisibility(View.GONE);
                hardButton.setVisibility(View.GONE);
                numberTipLabel.setVisibility(View.GONE);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel = false;
                level = Game.HARD;
                pointsLabel.setText(String.format(Locale.getDefault(), "%d", points));
                gameEngine.setDifficulty(Game.HARD_SPEED);
                scoreLabel.setVisibility(View.VISIBLE);
                pointsLabel.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
                easyButton.setVisibility(View.GONE);
                hardButton.setVisibility(View.GONE);
                numberTipLabel.setVisibility(View.GONE);
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable){
                    check(gameEngine.greenClick(greenButton));
                }
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable){
                    check(gameEngine.redClick(redButton));
                }
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable){
                    check(gameEngine.yellowClick(yellowButton));
                }
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable){
                    check(gameEngine.blueClick(blueButton));
                }
            }
        });

    }

    private void check(int res){
        if(res == Game.END){
            points++;
            clickable = false;
            pointsLabel.setText(String.format(Locale.getDefault(), "%d", points));
            numberTipLabel.setVisibility(View.GONE);
            startButton.setVisibility(View.VISIBLE);
        } else if(res == Game.RIGHT){
            points++;
            pointsLabel.setText(String.format(Locale.getDefault(), "%d", points));
        } else if (res == Game.WRONG){
            Intent intent = new Intent(this, EndGameScreen.class);
            intent.putExtra("level", level);
            intent.putExtra("points", points);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "An internal error has occurred!", Toast.LENGTH_LONG).show();
            cancel = true;
            onBackPressed();
        }
    }


    @Override
    public void onBackPressed() {
        if(!cancel){
            Toast.makeText(this, "Click once more to leave!", Toast.LENGTH_SHORT).show();
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
        gameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onRestart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        gameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onWindowFocusChanged(hasFocus);
    }
}
