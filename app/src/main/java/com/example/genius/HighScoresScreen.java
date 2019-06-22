package com.example.genius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.genius.Engine.Game;
import com.example.genius.Realm.Score;

import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.ParametersAreNonnullByDefault;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class HighScoresScreen extends AppCompatActivity {

    private Button backButton;
    private Button easyScores;
    private Button hardScores;
    private TextView score1;
    private TextView score2;
    private TextView score3;
    private TextView score4;
    private TextView score5;
    private TextView score6;
    private TextView score7;
    private TextView score8;
    private TextView levelLabel;
    private ArrayList<TextView> labels;
    private LinearLayout highScoresLayout;
    private final Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores_screen);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        highScoresLayout = findViewById(R.id.highScoresLayout);
        highScoresLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        assign();
        fillList();
        initializeAsEasy();

        easyScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelLabel.setText(R.string.easy);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    @ParametersAreNonnullByDefault
                    public void execute(Realm realm) {
                        RealmResults<Score> easyScores = realm.where(Score.class).equalTo("level", Game.EASY).sort("score", Sort.DESCENDING).findAll();

                        resetLabels();

                        for(int x = 0; x < 8 & x < easyScores.size(); x++){
                            final TextView text = labels.get(x);
                            final Score score = easyScores.get(x);
                            if(score == null) {
                                continue;
                            }
                            text.setText(String.format(Locale.getDefault(),"%d - %s.", score.getScore(), score.getName()));
                        }
                    }
                });
            }
        });

        hardScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelLabel.setText(R.string.hard);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    @ParametersAreNonnullByDefault
                    public void execute(Realm realm) {
                        RealmResults<Score> hardScores = realm.where(Score.class).equalTo("level", Game.HARD).sort("score", Sort.DESCENDING).findAll();

                        resetLabels();

                        for(int x = 0; x < 8 & x < hardScores.size(); x++){
                            final TextView text = labels.get(x);
                            final Score score = hardScores.get(x);
                            if(score == null) {
                                continue;
                            }
                            text.setText(String.format(Locale.getDefault(),"%d - %s.", score.getScore(), score.getName()));
                        }
                    }
                });
            }
        });

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
        highScoresLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onRestart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        highScoresLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onWindowFocusChanged(hasFocus);
    }

    private void assign() {
        score1 = findViewById(R.id.easyScore1);
        score2 = findViewById(R.id.easyScore2);
        score3 = findViewById(R.id.easyScore3);
        score4 = findViewById(R.id.easyScore4);
        score5 = findViewById(R.id.easyScore5);
        score6 = findViewById(R.id.easyScore6);
        score7 = findViewById(R.id.easyScore7);
        score8 = findViewById(R.id.easyScore8);
        levelLabel = findViewById(R.id.levelLabel);
        backButton = findViewById(R.id.backHSButton);
        easyScores = findViewById(R.id.easyScoresLabel);
        hardScores = findViewById(R.id.hardScoresLabel);
    }

    private void resetLabels() {
        for(int x = 0; x < 8; x++){
            labels.get(x).setText(R.string.loading);
        }
    }

    private void fillList() {
        labels = new ArrayList<>();
        labels.add(score1);
        labels.add(score2);
        labels.add(score3);
        labels.add(score4);
        labels.add(score5);
        labels.add(score6);
        labels.add(score7);
        labels.add(score8);
    }

    private void initializeAsEasy() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            @ParametersAreNonnullByDefault
            public void execute(Realm realm) {
                RealmResults<Score> easyScores = realm.where(Score.class).equalTo("level", Game.EASY).sort("score", Sort.DESCENDING).findAll();

                resetLabels();

                for(int x = 0; x < 8 & x < easyScores.size(); x++){
                    final TextView text = labels.get(x);
                    final Score score = easyScores.get(x);
                    if(score == null) {
                        continue;
                    }
                    text.setText(String.format(Locale.getDefault(),"%d by %s.", score.getScore(), score.getName()));
                }
            }
        });
    }

}
