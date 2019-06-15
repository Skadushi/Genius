package com.example.genius;

import java.util.Random;

public class Color {

    public static final int GREEN = 0;
    public static final int RED = 1;
    public static final int YELLOW = 2;
    public static final int BLUE = 3;
    //public static final int UNDEFINED = 4;

    public int color;

    public Color() {
        this.color = generateColor();
    }

    public static int generateColor(){
        return new Random().nextInt(4);
    }

    public Boolean isGreen(){
        return this.color == GREEN;
    }

    public Boolean isRed(){
        return this.color == RED;
    }

    public Boolean isYellow(){
        return this.color == YELLOW;
    }

    public Boolean isBlue(){
        return this.color == BLUE;
    }

}
