package com.example.sharping.arithmeticthiefcatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private GameView view;

    @Override
    public void onBackPressed(){
        view.backPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GameView(this, getApplicationContext());
        setContentView(view);
    }
}
