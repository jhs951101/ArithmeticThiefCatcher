package com.example.sharping.arithmeticthiefcatcher;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by SharPing on 2017-08-04.
 */

public abstract class SoundEffect {

    protected GameView gameView = null;
    protected SoundPool soundPool;

    protected int soundId;
    protected int streamId;

    public SoundEffect(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }
    }

    public void playSound(){
        streamId = soundPool.play(soundId, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public void gameStart(){
        gameView.getBtnReleasedEffect().playSound();
        gameView.setReleasedEffectState(gameView.getShootEffect());
        gameView.stopBgMusic();
    }

    public void gameEnd(){
        gameView.setReleasedEffectState(gameView.getBtnReleasedEffect());
    }
}
