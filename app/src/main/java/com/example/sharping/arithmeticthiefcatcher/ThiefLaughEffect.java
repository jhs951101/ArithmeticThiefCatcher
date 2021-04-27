package com.example.sharping.arithmeticthiefcatcher;

import android.content.Context;

/**
 * Created by SharPing on 2017-01-31.
 */

public class ThiefLaughEffect extends SoundEffect {

    public ThiefLaughEffect(Context c){
        soundId = soundPool.load(c, R.raw.zombielaugh, 1);
    }
}
