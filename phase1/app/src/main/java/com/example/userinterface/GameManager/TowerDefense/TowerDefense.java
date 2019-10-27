package com.example.userinterface.GameManager.TowerDefense;

import android.graphics.Canvas;

import java.util.ArrayList;

public class TowerDefense {
    //User user;
    int money = 200;
    int waves = 1;
    private int lives = 3;
    private int mapHeight;
    private int mapWidth;
    private boolean win = true;



    private ArrayList<Enemy> wave1 = new ArrayList<>();

    private int clicker=0;

    public TowerDefense(int screenWidth, int screenHeight) {
        mapHeight = screenHeight;
        mapWidth = screenWidth;
    }

    public void update(){

        Enemy enemy = getFirstEnemy();
        enemy.hit(clicker);
        clicker=0;
        ArrayList<Enemy> temp = new ArrayList<>();
        for (Enemy e: wave1){
            if (e.getHealth() < 0){
                temp.add(e);
            }
            if (e.getY() >= mapHeight-300) {
                temp.add(e);
                lives -= 1;
                if (lives <= 0)
                    win = false;
            }
            e.move();
        }
        for (Enemy item: temp){
            wave1.remove(item);
        }
    }


    public Enemy getFirstEnemy(){
        int yCoor = -mapHeight/2-100;  //the highest enemy is half the map height above
        Enemy firstEnemy = new Minion();
        for (Enemy item: wave1){
            if (item.getY()>yCoor){
                firstEnemy = item;
                yCoor = item.getY();
            }
        }
        return firstEnemy;
    }

    public void addEnemy(){
        addMinion();
        }

    private void addMinion() {
        for (int i = 0; i < 10; i++) {
            Minion minion = new Minion();
            int x = (int) (Math.random() * mapWidth);
            int y = -(int) (Math.random()* mapHeight/2)-100; // a period of time for enemies to walk
            minion.setLocation(x, y);
            wave1.add(minion);
        }
    }


    public void draw(Canvas canvas){
        for (Enemy item: wave1){
            item.draw(canvas);
        }

    }
    public void clicked(){clicker += 1;}

    public ArrayList<Enemy> getWave1() { return wave1; }

    public boolean getWin(){return win;}
}
