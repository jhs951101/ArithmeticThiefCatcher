package com.example.sharping.arithmeticthiefcatcher;

import android.content.Context;

/**
 * Created by SharPing on 2017-01-31.
 */

public class ThiefDeadEffect extends SoundEffect {

    public ThiefDeadEffect(Context c){
        soundId = soundPool.load(c, R.raw.zombiedead, 1);
    }
}
