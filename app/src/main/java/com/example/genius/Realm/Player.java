package com.example.genius.Realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Player extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Score> scores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Score> getScores() {
        return scores;
    }

    public void setScores(RealmList<Score> scores) {
        this.scores = scores;
    }
}
