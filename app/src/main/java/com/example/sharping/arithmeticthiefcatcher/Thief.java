package com.example.sharping.arithmeticthiefcatcher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-07-27.
 */

public class Thief extends Thread {
    
    private int x;
    private int y;

    private long startedTime;
    private long limitTime;

    private Bitmap[] tImage = new Bitmap[5];

    private int[] tImageDeadIds = {R.drawable.thief1withboarddead, R.drawable.thief2withboarddead};

    private int[][] tImageIds =
            {{R.drawable.thief1withboard1, R.drawable.thief1withboard2, R.drawable.thief1withboard3, R.drawable.thief1withboard4, R.drawable.thief1withboard5},
            {R.drawable.thief2withboard1, R.drawable.thief2withboard2, R.drawable.thief2withboard3, R.drawable.thief2withboard4, R.drawable.thief2withboard5}};

    private ArrayList<Thief> thiefs;
    private Bitmap tImageDead;
    private Length tImageLength;
    private ImageString imageNumbers;

    private int tImageChange;
    // 5만큼 증가할 때마다 이미지가 바뀜

    private short tImageState;
    // tImage 0 ~ 4 + 1

    private SoundEffect thiefLaughEffect;
    private SoundEffect thiefDeadEffect;
    private DeadEffectTimer deadEffectTimer;
    private ThiefDeadEffectShow thiefDeadEffectShow;

    private int thiefValue;
    private int gameMode;

    private boolean available;
    private boolean timeout;

    public Flag flagClass;

    public Thief(Resources r, Context c, ArrayList<Thief> ts, int x, int y, long lt, int gm){
        this.thiefs = ts;
        this.x = x;
        this.y = y;
        this.limitTime = lt;
        this.gameMode = gm;

        thiefValue = getRandomNumber(10, 99);
        tImageChange = 0;
        tImageState = 0;

        flagClass = new Flag(true);
        imageNumbers = new ImageString(flagClass, 1);
        imageNumbers.setImageString(String.valueOf(thiefValue));
        thiefLaughEffect = new ThiefLaughEffect(c);
        thiefDeadEffect = new ThiefDeadEffect(c);
        thiefDeadEffectShow = new ThiefDeadEffectShow();

        int i = getRandomNumber(0, 1);
        for(int j=0; j<5; j++){
            tImage[j] = BitmapFactory.decodeResource(r, tImageIds[i][j]);
        }

        tImageDead = BitmapFactory.decodeResource(r, tImageDeadIds[i]);

        tImageLength = new Length(tImage[4].getWidth(), tImage[4].getHeight());
        available = false;
        timeout = false;
    }

    public int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max-min+1)) + min;
    }

    @Override
    public void run(){
        try {
            while (tImageState < 4) {
                tImageChange++;

                if (tImageChange >= 1) {
                    tImageState++;
                    tImageChange = 0;
                }

                if(GameView.gameFlag == 1 && flagClass.flag)
                    Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startedTime = System.currentTimeMillis();
        available = true;

        while(GameView.gameFlag == 1 && flagClass.flag){
            if(System.currentTimeMillis() - startedTime >= limitTime){
                available = false;
                timeout = true;
                break;
            }
        }

        try{
            while(GameView.gameFlag == 1 && flagClass.flag && tImageState > 0) {
                tImageChange++;

                if (tImageChange >= 1) {
                    tImageState--;
                    tImageChange = 0;
                }

                if(GameView.gameFlag == 1 && flagClass.flag)
                    Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flagClass.flag = false;

        for(int i=0; i<5; i++) {
            tImage[i].recycle();
            tImage[i] = null;
        }

        tImageDead.recycle();
        tImageDead = null;

        thiefs.remove(this);
    }

    public boolean minusStamina(int ownValue){  // true: 승리
        if(gameMode == 1){
            if(ownValue > thiefValue)
                return true;
            else
                return false;
        }
        else if(gameMode == 2){
            if(ownValue < thiefValue)
                return true;
            else
                return false;
        }
        else {
            if(ownValue == thiefValue)
                return true;
            else
                return false;
        }
    }

    public int getCenterPosition(int pos, int length){
        return pos - (length/2);
    }

    public void draw(Canvas canvas){
        if(thiefDeadEffectShow.show){
            if (GameView.gameFlag == 1 && flagClass.flag)
                canvas.drawBitmap(tImageDead, x, y, null);
        }
        else {
            if (tImageState == 0) {
                if (GameView.gameFlag == 1 && flagClass.flag)
                    canvas.drawBitmap(tImage[0], x, y, null);
            } else if (tImageState == 1) {
                if (GameView.gameFlag == 1 && flagClass.flag)
                    canvas.drawBitmap(tImage[1], x, y, null);
            } else if (tImageState == 2) {
                if (GameView.gameFlag == 1 && flagClass.flag)
                    canvas.drawBitmap(tImage[2], x, y, null);
            } else if (tImageState == 3) {
                if (GameView.gameFlag == 1 && flagClass.flag)
                    canvas.drawBitmap(tImage[3], x, y, null);
            } else if (tImageState == 4) {
                if (GameView.gameFlag == 1 && flagClass.flag) {
                    canvas.drawBitmap(tImage[4], x, y, null);

                    if (available) {
                        imageNumbers.drawImageString(canvas, getCenterPosition(x + tImageLength.getWidth() / 2, imageNumbers.getWidth()),
                                getCenterPosition(y + (tImageLength.getHeight() * 3 / 4), imageNumbers.getHeight()));
                    }
                }
            }
        }
    }

    public void turnOnDeadEffectTImer(){
        available = false;
        deadEffectTimer = new DeadEffectTimer(thiefs, this, thiefDeadEffectShow);
        deadEffectTimer.setLimitTime(100);
        deadEffectTimer.start();
        thiefDeadEffect.playSound();
    }

    public void playLaughEffect(){
        thiefLaughEffect.playSound();
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return tImageLength.getWidth();
    }

    public int getHeight(){
        return tImageLength.getHeight();
    }

    public boolean isAvailable(){
        return this.available;
    }

    public boolean isTimeout(){
        return this.timeout;
    }

    public void setTimeoutDisable(){
        this.timeout = false;
    }

    public int getThiefValue(){
        return this.thiefValue;
    }
}
