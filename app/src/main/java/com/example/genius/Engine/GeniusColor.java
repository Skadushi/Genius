package com.example.genius.Engine;

import java.util.Random;

public class GeniusColor {

    public static final int GREEN = 0;
    public static final int RED = 1;
    public static final int YELLOW = 2;
    public static final int BLUE = 3;

    public int color;

    public GeniusColor() {
        this.color = generateColor();
    }

    public static int generateColor(){
        return new Random().nextInt(4);
    }
}
