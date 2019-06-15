package com.example.genius;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.LogRecord;

public class GameEngine {

    private ArrayList<Color> colors;
    private long difficulty;

    public GameEngine() {
        colors = new ArrayList<>();
        for (int x = 0; x < 2; x++) {
            generateNext();
        }
    }

    private void generateNext(){
        colors.add(new Color());
    }

    public void setDifficulty(long difficulty) {
        this.difficulty = difficulty;
    }

    public void showColors(LinearLayout coloredLayout, TextView numberTipLabel){
        generateNext();
        recursiveColorChange(coloredLayout, numberTipLabel, 0);
    }

    private void recursiveColorChange(final LinearLayout coloredLayout, final TextView tip, Integer x) {
        final int i = x + 1;
        System.out.println(x);
        if(x < colors.size()){
            switch (colors.get(x).color){
                case Color.GREEN:
                    coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.green));
                    tip.setText(x.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recursiveColorChange(coloredLayout, tip, i);
                        }
                    }, difficulty);
                    break;
                case Color.RED:
                    coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.red));
                    tip.setText(x.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recursiveColorChange(coloredLayout, tip, i);
                        }
                    }, difficulty);
                    break;
                case Color.YELLOW:
                    coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.yellow));
                    tip.setText(x.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recursiveColorChange(coloredLayout, tip, i);
                        }
                    }, difficulty);
                    break;
                case Color.BLUE:
                    coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.blue));
                    tip.setText(x.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recursiveColorChange(coloredLayout, tip, i);
                        }
                    }, difficulty);
                    break;
            }
        } else {
            coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.colorPrimary));
            tip.setText("");
        }
    }


}


//Fix this, consuming too much processing power
//    public void play(ImageView green, ImageView red, ImageView yellow, ImageView blue){
//        int iteration = 0;
//        long time = System.currentTimeMillis();
//        int beingUsed = Color.UNDEFINED;
//        while(iteration < colors.size()){
//             if(beingUsed != Color.UNDEFINED){
//                 if((System.currentTimeMillis() - time) >= difficulty){
//                     switch (beingUsed){
//                         case Color.GREEN:
//                             green.setImageResource(R.drawable.green);
//                             beingUsed = Color.UNDEFINED;
//                             iteration++;
//                             time = System.currentTimeMillis();
//                             break;
//
//                         case Color.RED:
//                             red.setImageResource(R.drawable.red);
//                             beingUsed = Color.UNDEFINED;
//                             iteration++;
//                             time = System.currentTimeMillis();
//                             break;
//
//                         case Color.YELLOW:
//                             yellow.setImageResource(R.drawable.yellow);
//                             beingUsed = Color.UNDEFINED;
//                             iteration++;
//                             time = System.currentTimeMillis();
//                             break;
//
//                         case Color.BLUE:
//                             blue.setImageResource(R.drawable.blue);
//                             beingUsed = Color.UNDEFINED;
//                             iteration++;
//                             time = System.currentTimeMillis();
//                             break;
//                     }
//                 }
//             } else {
//                 switch (colors.get(iteration).color){
//                     case Color.GREEN:
//                         green.setImageResource(R.drawable.green_pressed);
//                         beingUsed = Color.GREEN;
//                         break;
//
//                     case Color.RED:
//                         red.setImageResource(R.drawable.red_pressed);
//                         beingUsed = Color.RED;
//                         break;
//
//                     case Color.YELLOW:
//                         yellow.setImageResource(R.drawable.yellow_pressed);
//                         beingUsed = Color.YELLOW;
//                         break;
//
//                     case Color.BLUE:
//                         blue.setImageResource(R.drawable.blue_pressed);
//                         beingUsed = Color.BLUE;
//                         break;
//                 }
//             }
//        }
//    }


