package com.example.sharping.arithmeticthiefcatcher;

import android.content.Context;

/**
 * Created by SharPing on 2017-01-31.
 */

public class GunEffect extends SoundEffect {

    public GunEffect(GameView gv, Context c){
        gameView = gv;
        soundId = soundPool.load(c, R.raw.guneffect, 1);
    }
}
