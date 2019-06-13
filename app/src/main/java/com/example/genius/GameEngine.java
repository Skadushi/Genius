package com.example.genius;

import java.util.ArrayList;

public class GameEngine {

    public ArrayList<Color> colors;

    public GameEngine() {
        colors = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            generateNext();
        }
    }

    public void generateNext(){
        colors.add(new Color());
    }

}
