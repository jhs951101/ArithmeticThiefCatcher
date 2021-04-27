package com.example.sharping.arithmeticthiefcatcher;

/**
 * Created by SharPing on 2017-08-10.
 */

public class Player {

    ImageString playerImageString;

    private double MAX_HEALTH = 100.0;
    private int playerHealth;
    private int playerValue;

    public Player(){
        playerImageString = new ImageString(GameView.gameFlagClass, 0);
        resetHealth();
        initializePlayerValue();
    }

    public int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max-min+1)) + min;
    }

    public void initializePlayerValue(){
        playerValue = getRandomNumber(10, 99);
    }

    public void resetHealth(){
        playerHealth = (int) MAX_HEALTH;
    }

    public void minusHeaith(int damage){
        playerHealth -= damage;
    }

    public void setPlayerValue(int pv){
        this.playerValue = pv;
        this.playerImageString.setImageString(String.valueOf(pv));
    }

    public int getHealth(){
        return this.playerHealth;
    }

    public int getPlayerValue(){
        return this.playerValue;
    }
}
