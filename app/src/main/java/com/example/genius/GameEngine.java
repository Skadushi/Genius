package com.example.genius;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameEngine {

    private ArrayList<GeniusColor> colors;
    private long difficulty;
    private int current;

    public GameEngine() {
        current = 0;
        colors = new ArrayList<>();
        for (int x = 0; x < 2; x++) {
            generateNext();
        }
    }

    public void generateNext(){
        colors.add(new GeniusColor());
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
        if(x < colors.size()){
            switch (colors.get(x).color){
                case GeniusColor.GREEN:
                    coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.green));
                    tip.setText(x.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recursiveColorChange(coloredLayout, tip, i);
                        }
                    }, difficulty);
                    break;
                case GeniusColor.RED:
                    coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.red));
                    tip.setText(x.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recursiveColorChange(coloredLayout, tip, i);
                        }
                    }, difficulty);
                    break;
                case GeniusColor.YELLOW:
                    coloredLayout.setBackgroundColor(coloredLayout.getContext().getResources().getColor(R.color.yellow));
                    tip.setText(x.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recursiveColorChange(coloredLayout, tip, i);
                        }
                    }, difficulty);
                    break;
                case GeniusColor.BLUE:
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
            GameScreen.clickable = true;
        }
    }

    private Boolean levelEnded(){
        if(current == colors.size()){
            return true;
        }

        return false;
    }

    public int greenClick(final ImageView pressed){
        pressed.setImageResource(R.drawable.green_pressed);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pressed.setImageResource(R.drawable.green);
            }
        }, 100);

        if(colors.get(current).color == GeniusColor.GREEN){
            current++;
            if(levelEnded()){
                current = 0;
                return Game.END;
            }
            return Game.RIGHT;
        } else {
            return Game.WRONG;
        }
    }

    public int redClick(final ImageView pressed){
        pressed.setImageResource(R.drawable.red_pressed);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pressed.setImageResource(R.drawable.red);
            }
        }, 100);

        if(colors.get(current).color == GeniusColor.RED){
            current++;
            if(levelEnded()){
                current = 0;
                return Game.END;
            }
            return Game.RIGHT;
        } else {
            return Game.WRONG;
        }
    }

    public int yellowClick(final ImageView pressed){
        pressed.setImageResource(R.drawable.yellow_pressed);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pressed.setImageResource(R.drawable.yellow);
            }
        }, 100);

        if(colors.get(current).color == GeniusColor.YELLOW){
            current++;
            if(levelEnded()){
                current = 0;
                return Game.END;
            }
            return Game.RIGHT;
        } else {
            return Game.WRONG;
        }
    }

    public int blueClick(final ImageView pressed){
        pressed.setImageResource(R.drawable.blue_pressed);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pressed.setImageResource(R.drawable.blue);
            }
        }, 100);

        if(colors.get(current).color == GeniusColor.BLUE){
            current++;
            if(levelEnded()){
                current = 0;
                return Game.END;
            }
            return Game.RIGHT;
        } else {
            return Game.WRONG;
        }
    }

}

