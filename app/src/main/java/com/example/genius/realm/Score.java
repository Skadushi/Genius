package com.example.genius.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Score extends RealmObject {

    public static int id = 0;

    @PrimaryKey
    private int idScore;
    private int score;
    private int level;
    private String name;

    public Score() {}

    public Score(int idScore, int score, int level, String nome) {
        this.idScore = idScore;
        this.score = score;
        this.level = level;
        this.name = nome;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
