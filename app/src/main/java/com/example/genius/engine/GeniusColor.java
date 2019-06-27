package com.example.genius.engine;

import java.util.Random;

public class GeniusColor {

    static final int GREEN = 0;
    static final int RED = 1;
    static final int YELLOW = 2;
    static final int BLUE = 3;

    public int color;

    GeniusColor() {
        this.color = generateColor();
    }

    private static int generateColor() {
        return new Random().nextInt(4);
    }
}
