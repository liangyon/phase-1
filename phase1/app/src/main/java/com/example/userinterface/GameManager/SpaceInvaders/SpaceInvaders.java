package com.example.userinterface.GameManager.SpaceInvaders;

import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders implements Observer{
    // Constants
    static int X = 0;
    static int Y = 1;

    // Private attributes
    private int height;
    private int width;
    private Player player;
    private List<SpaceObject> subjects = new ArrayList<>();

    // Initializer
    public SpaceInvaders(int width, int height) {
        this.height = height;
        this.width = width;
    }

    // Implements Observer
    // Register subject to observe
    public void registerSubject(Subject subject){
        // if this is an enemy ship, it's subject will be player and player's bullet;
        // player, enemy and their bullets.
        if (!subjects.contains((SpaceObject) subject))
            subjects.add((SpaceObject) subject);
    }

    public void unregisterSubject(Subject subject) {
        subjects.remove((SpaceObject) subject);
    }

    public void unregisterAll() {
        subjects.clear();
    }

    // update machine-controlled objects
    // this is the responsibility as a SpaceInvader manager
    void run() {
        ArrayList<SpaceObject> subjectsToRemove = new ArrayList<>();
        ArrayList<SpaceObject> subjectsToMove = new ArrayList<>();
        for (SpaceObject obj : subjects) {
            if (obj.isUpdated())
                continue;
            if (obj instanceof Bullet) {
                if (isAboutBounceBack(obj) || isAtBorder(obj)) {
                    // eliminate bullet & enemy when it flies out
                    ((Subject) obj).unregisterAllObservers();
                    subjectsToRemove.add(obj);
                } else
                    subjectsToMove.add(obj);
            } else if (obj instanceof Enemy) {
                if (isAtBorder(obj)) {
                    ((Subject) obj).unregisterAllObservers();
                    subjectsToRemove.add(obj);
                } else if (isAboutBounceBack(obj))
                    // reverse the heading of enemy
                    obj.setXSpeed(-obj.getXSpeed());
                else
                    subjectsToMove.add(obj);
            }
        }
        for (SpaceObject obj : subjectsToRemove)
            subjects.remove(obj);
        for (SpaceObject obj : subjectsToMove)
            if (obj instanceof Bullet) {
                ((Bullet) obj).move();
            } else if (obj instanceof Enemy) {
                ((Enemy) obj).move();
            }
        for (SpaceObject obj : subjects) {
            obj.setUpdated(false);
        }
    }

    // actual update of the observer
    public void update() {
        int[] newPosition;
        for (Subject sub : subjects) {
            newPosition = sub.getUpdate(this);
        }
    }

    // Utils
    public void layout() {
        this.player = new Player((this.width >> 1), 1300, 0, 300);
        this.player.registerObserver(this);
        subjects.add(this.player);

        for (int x = 100; x < 800; x += 100)
            subjects.add(new Enemy(x, 100, 100, 3, 100));
        for (Subject sub : subjects)
            sub.registerObserver(this);
    }

    // change heading when meet x border
    private boolean isAboutBounceBack(@NotNull SpaceObject obj) {
        int nextX = obj.getX() + obj.getXSpeed();
        return (nextX <= 0) || (nextX >= width);
    }

    // only eliminate object when it is at y border
    private boolean isAtBorder(@NotNull SpaceObject obj) {
        int nextY = obj.getY() + obj.getYSpeed();
        return (nextY <= 0) || (nextY >= height);
    }

    public void draw(Canvas canvas) {
        for (SpaceObject item : subjects) item.draw(canvas);
        this.player.draw(canvas);
    }

    void goLeft() {
//        moveDelay = 1;
//        command = -1;
        // If the player is at the border, then it can't go further
//        if (isAtBorder(player))
//            player.move(0);
//        else
        System.out.println(this.player == null);
        this.player.move(-1);
    }
    void goRight() {
//        moveDelay = 1;
//        command = 1;
//        if (isAtBorder(player))
//            player.move(0);
//        else
        System.out.println(this.player == null);
        this.player.move(1);
    }
}
