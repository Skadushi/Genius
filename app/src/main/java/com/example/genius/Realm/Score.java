package com.example.genius.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Score extends RealmObject {
    @PrimaryKey
    private int idScore;
    private int score;
    private int idPlayer;
    private int level;

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
