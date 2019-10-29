package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Color;

public class Player extends Ship {

    public Player(float x, float y, int lives){
        super(x,y,lives);
        this.appearance = "P";
        this.paintText.setColor(Color.CYAN);
        this.paintText.setTextSize(36);
    }

    @Override
    public void move(){
        //to be implemented as a touch event
    }

    @Override
    public Bullet shoot(){
        //fires bullet objects that damage player
        return new Bullet(this.getX(), this.getY()-1, -15);
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawText(this.appearance, this.getX(), this.getY(), paintText);
    }
}
