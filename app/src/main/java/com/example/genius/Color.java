package com.example.genius;

import java.util.Random;

public class Color {

    private static final int greenValue = 0;
    private static final int redValue = 1;
    private static final int yellowValue = 2;
    private static final int blueValue = 3;

    public int color;

    public Color() {
        this.color = generateColor();
    }

    public static int generateColor(){
        return new Random().nextInt(4);
    }


}
