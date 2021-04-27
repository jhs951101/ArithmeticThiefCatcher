package com.example.sharping.arithmeticthiefcatcher;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by SharPing on 2017-08-11.
 */

public class ImageString {

    private Bitmap[] values;
    private Flag flagClass;
    private int type;

    public ImageString(Flag fc, int t){
        this.flagClass = fc;
        this.type = t;
    }

    public void setImageString(String str){
        values = new Bitmap[str.length()];
        int i = str.length()-1;

        while(i >= 0){
            char c = str.charAt(i);

            if(c == ' '){
                values[i] = GameView.IMG_CHAR_BLANK;
            }
            else if(c == '%') {
                values[i] = GameView.IMG_CHAR_PERCENTAGE;
            }
            else if(c == ':') {
                values[i] = GameView.IMG_CHAR_DANGDANG;
            }
            else if(c == '-') {
                values[i] = GameView.IMG_CHAR_MINUS;
            }
            else if(c == 'a') {
                values[i] = GameView.IMG_ALPABET_a;
            }
            else if(c == 'c') {
                values[i] = GameView.IMG_ALPABET_c;
            }
            else if(c == 'e') {
                values[i] = GameView.IMG_ALPABET_e;
            }
            else if(c == 'g') {
                values[i] = GameView.IMG_ALPABET_g;
            }
            else if(c == 'i') {
                values[i] = GameView.IMG_ALPABET_i;
            }
            else if(c == 'm') {
                values[i] = GameView.IMG_ALPABET_m;
            }
            else if(c == 'o') {
                values[i] = GameView.IMG_ALPABET_o;
            }
            else if(c == 'r') {
                values[i] = GameView.IMG_ALPABET_r;
            }
            else if(c == 's') {
                values[i] = GameView.IMG_ALPABET_s;
            }
            else if(c == 't') {
                values[i] = GameView.IMG_ALPABET_t;
            }
            else if(c >= '0' && c <= '9'){
                values[i] = GameView.IMG_NUMBERS[type][c - 48];
            }

            i--;
        }
    }

    public void drawImageString(Canvas canvas, int x, int y){
        if(flagClass.flag)
            canvas.drawBitmap(values[0], x, y, null);

        int drawX = x;
        int drawY = y + values[0].getHeight();
        int i = 1;

        while(i < values.length) {
            drawX += values[i-1].getWidth();

            if(flagClass.flag)
                canvas.drawBitmap(values[i], drawX, drawY - values[i].getHeight(), null);

            i++;
        }
    }

    public int getWidth(){
        int result = 0;

        for(int i=0; i<values.length; i++) {
            result += values[i].getWidth();
        }

        return result;
    }

    public int getHeight(){
        int result = values[0].getHeight();

        for(int i=1; i<values.length; i++) {
            if(result < values[i].getWidth())
                result = values[i].getWidth();
        }

        return result;
    }
}
