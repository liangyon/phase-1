package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

class ScoreBoardView {
    public void draw(Canvas canvas, Scoreboard scoreboard){
        canvas.drawText(scoreboard.getAppearance(), 250, scoreboard.getHeight() - 250, scoreboard.getPaintText());
        canvas.drawText("HP", 250, scoreboard.getHeight() - 200, scoreboard.getPaintText());
        canvas.drawRect(320, scoreboard.getHeight() - 220, scoreboard.getLives() + 300, scoreboard.getHeight() - 200, scoreboard.getPaintText());
        canvas.drawText("Stage: " + scoreboard.getLevel(), 40, 40, scoreboard.getPaintText());
    }
}
