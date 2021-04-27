package com.example.sharping.arithmeticthiefcatcher;

import java.util.ArrayList;

/**
 * Created by SharPing on 2017-08-04.
 */

public class DeadEffectTimer extends CountTimer {

    private ArrayList<Thief> zombies;
    private Thief zombie;
    private ThiefDeadEffectShow zombieDeadEffectShow;

    public DeadEffectTimer(ArrayList<Thief> zs, Thief z, ThiefDeadEffectShow zdes){
        this.zombies = zs;
        this.zombie = z;
        this.zombieDeadEffectShow = zdes;
    }

    @Override
    public void run(){
        startedTime = System.currentTimeMillis();
        zombieDeadEffectShow.show = true;

        while((GameView.gameFlag == 1 || GameView.gameFlag == 3) && flag){
            if(System.currentTimeMillis() - startedTime >= limitTime){
                flag = false;
                break;
            }
        }

        zombie.flagClass.flag = false;
        zombies.remove(zombie);

        zombieDeadEffectShow.show = false;
    }
}