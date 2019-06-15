package com.example.genius;

import android.widget.ImageView;

import java.util.ArrayList;

public class GameEngine {

    private ArrayList<Color> colors;
    private long difficulty;

    public GameEngine() {
        colors = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            generateNext();
        }
    }

    public void setDifficulty(long difficulty) {
        this.difficulty = difficulty;
    }

    public void generateNext(){
        colors.add(new Color());
    }


    //Fix this, consuming too much processing power
    public void play(ImageView green, ImageView red, ImageView yellow, ImageView blue){
        int iteration = 0;
        long time = System.currentTimeMillis();
        int beingUsed = Color.UNDEFINED;
        while(iteration < colors.size()){
             if(beingUsed != Color.UNDEFINED){
                 if((System.currentTimeMillis() - time) >= difficulty){
                     switch (beingUsed){
                         case Color.GREEN:
                             green.setImageResource(R.drawable.green);
                             beingUsed = Color.UNDEFINED;
                             iteration++;
                             time = System.currentTimeMillis();
                             break;

                         case Color.RED:
                             red.setImageResource(R.drawable.red);
                             beingUsed = Color.UNDEFINED;
                             iteration++;
                             time = System.currentTimeMillis();
                             break;

                         case Color.YELLOW:
                             yellow.setImageResource(R.drawable.yellow);
                             beingUsed = Color.UNDEFINED;
                             iteration++;
                             time = System.currentTimeMillis();
                             break;

                         case Color.BLUE:
                             blue.setImageResource(R.drawable.blue);
                             beingUsed = Color.UNDEFINED;
                             iteration++;
                             time = System.currentTimeMillis();
                             break;
                     }
                 }
             } else {
                 switch (colors.get(iteration).color){
                     case Color.GREEN:
                         green.setImageResource(R.drawable.green_pressed);
                         beingUsed = Color.GREEN;
                         break;

                     case Color.RED:
                         red.setImageResource(R.drawable.red_pressed);
                         beingUsed = Color.RED;
                         break;

                     case Color.YELLOW:
                         yellow.setImageResource(R.drawable.yellow_pressed);
                         beingUsed = Color.YELLOW;
                         break;

                     case Color.BLUE:
                         blue.setImageResource(R.drawable.blue_pressed);
                         beingUsed = Color.BLUE;
                         break;
                 }
             }
        }
    }

}
