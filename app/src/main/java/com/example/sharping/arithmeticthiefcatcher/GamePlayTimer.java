package com.example.sharping.arithmeticthiefcatcher;

/**
 * Created by SharPing on 2017-08-10.
 */

public class GamePlayTimer extends CountTimer {

    private boolean timeup;

    @Override
    public void run(){
        timeup = false;
        startedTime = System.currentTimeMillis();

        while(GameView.gameFlag == 1 && flag){
            if(System.currentTimeMillis() - startedTime >= limitTime){
                timeup = true;
                flag = false;
                break;
            }
        }

        if(timeup) {
            GameView.gameFlag = 2;
            GameView.gameFlagClass.flag = false;
        }
    }

    public long getRemainTime(){
        return limitTime - (System.currentTimeMillis() - startedTime);
    }
}
