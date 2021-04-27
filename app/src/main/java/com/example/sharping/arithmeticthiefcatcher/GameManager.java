package com.example.sharping.arithmeticthiefcatcher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-07-18.
 */

public class GameManager extends Thread {

    private Resources resources;
    private Context context;

    private Player player;
    private ArrayList<Thief> thiefs;
    private Length thiefLength;

    private long startedTime;
    private int gameMode;
    private int stage;
    private int thiefLastValue = 0;
    private int allNumofThief;

    private boolean open;

    public GameManager(Resources r, Context context, Player p, ArrayList<Thief> c, int gm, int s){
        this.resources = r;
        this.context = context;
        this.player = p;
        this.thiefs = c;
        this.gameMode = gm;
        this.stage = s;
        allNumofThief = 0;

        Bitmap thief = BitmapFactory.decodeResource(r, R.drawable.thief1withboard5);
        thiefLength = new Length(thief.getWidth(), thief.getHeight());

        thief.recycle();
        thief = null;

        open = false;
    }

    public int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max-min+1)) + min;
    }

    @Override
    public void run(){
        startedTime = System.currentTimeMillis();

        if(stage == 1) {  // Stage: 1
            while(GameView.gameFlag == 1){
                if(System.currentTimeMillis() - startedTime >= 5000 && GameView.gameFlag == 1){
                    int x = getRandomNumber(10, GameView.WIDTH - thiefLength.getWidth() - 10);
                    int y = getRandomNumber(10, GameView.HEIGHT/2);

                    Thief smallthief1 = new Thief(resources, context, thiefs, x, y, 3000, gameMode);

                    if(open) {
                        player.setPlayerValue(thiefLastValue);
                    }
                    else{
                        open = true;
                    }

                    smallthief1.start();
                    thiefs.add(smallthief1);
                    allNumofThief++;

                    thiefLastValue = smallthief1.getThiefValue();
                    startedTime = System.currentTimeMillis();
                }
            }
        }
    }

    public int getAllNumofThief(){
        return this.allNumofThief;
    }
}

