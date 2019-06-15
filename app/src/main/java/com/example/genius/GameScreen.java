package com.example.genius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameScreen extends AppCompatActivity {

    public static Boolean clickable;
    private int cancel;
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
        cancel = 1;
        clickable = false;

        getSupportActionBar().hide();
        gameLayout = findViewById(R.id.gameLinearLayout);
        gameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN);
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
                cancel = 0;
                numberTipLabel.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.GONE);
                gameEngine.showColors(coloredLayout, numberTipLabel);
                clickable = false;
            }
        });

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel = 0;
                pointsLabel.setText("0");
                gameEngine.setDifficulty(750);
                scoreLabel.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
                pointsLabel.setVisibility(View.VISIBLE);
                easyButton.setVisibility(View.GONE);
                hardButton.setVisibility(View.GONE);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel = 0;
                pointsLabel.setText("0");
                gameEngine.setDifficulty(375);
                scoreLabel.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
                pointsLabel.setVisibility(View.VISIBLE);
                easyButton.setVisibility(View.GONE);
                hardButton.setVisibility(View.GONE);
            }
        });
    }

    public void greenPressed(View view){
        if(clickable){
            //TODO
        }
    }

    @Override
    public void onBackPressed() {
        if(cancel == 0){
            Toast.makeText(this, "Click once more to leave!", Toast.LENGTH_SHORT).show();
            cancel++;
        } else {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        }
    }

    @Override
    protected void onRestart() {
        cancel = 0;
        gameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN);
        super.onRestart();
    }
}
