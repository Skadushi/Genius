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
    private LinearLayout highScoresLayout;
    private TextView easyScore1;
    private TextView easyScore2;
    private TextView easyScore3;
    private TextView easyScore4;
    private TextView easyScore5;
    private TextView hardScore1;
    private TextView hardScore2;
    private TextView hardScore3;
    private TextView hardScore4;
    private TextView hardScore5;
    private ArrayList<TextView> easyLabels;
    private ArrayList<TextView> hardLabels;
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

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            @ParametersAreNonnullByDefault
            public void execute(Realm realm) {
                RealmResults<Score> easyScores = realm.where(Score.class).equalTo("level", Game.EASY).sort("score", Sort.DESCENDING).findAll();
                RealmResults<Score> hardScores = realm.where(Score.class).equalTo("level", Game.HARD).sort("score", Sort.DESCENDING).findAll();

                for(int x = 0; x < 5 & x < easyScores.size(); x++){
                    final TextView text = easyLabels.get(x);
                    final Score score = easyScores.get(x);
                    if(score == null) {
                        continue;
                    }
                    text.setText(String.format(Locale.getDefault(),"%d by %s.", score.getScore(), score.getName()));
                }

                for(int x = 0; x < 5 & x < hardScores.size(); x++){
                    final TextView text = hardLabels.get(x);
                    final Score score = hardScores.get(x);
                    if(score == null) {
                        continue;
                    }
                    text.setText(String.format(Locale.getDefault(),"%d by %s.", score.getScore(), score.getName()));
                }
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
        easyScore1 = findViewById(R.id.easyScore1);
        easyScore2 = findViewById(R.id.easyScore2);
        easyScore3 = findViewById(R.id.easyScore3);
        easyScore4 = findViewById(R.id.easyScore4);
        easyScore5 = findViewById(R.id.easyScore5);
        hardScore1 = findViewById(R.id.hardScore1);
        hardScore2 = findViewById(R.id.hardScore2);
        hardScore3 = findViewById(R.id.hardScore3);
        hardScore4 = findViewById(R.id.hardScore4);
        hardScore5 = findViewById(R.id.hardScore5);
        backButton = findViewById(R.id.backHSButton);
    }

    private void fillList() {
        easyLabels = new ArrayList<>();
        hardLabels = new ArrayList<>();
        easyLabels.add(easyScore1);
        easyLabels.add(easyScore2);
        easyLabels.add(easyScore3);
        easyLabels.add(easyScore4);
        easyLabels.add(easyScore5);
        hardLabels.add(hardScore1);
        hardLabels.add(hardScore2);
        hardLabels.add(hardScore3);
        hardLabels.add(hardScore4);
        hardLabels.add(hardScore5);
    }

}
