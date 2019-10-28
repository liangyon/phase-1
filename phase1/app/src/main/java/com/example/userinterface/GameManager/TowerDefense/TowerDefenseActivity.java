package com.example.userinterface.GameManager.TowerDefense;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.userinterface.R;

import java.util.ArrayList;

public class TowerDefenseActivity extends Activity {

    Button btnStart, btnHit, btnTower1, btnTower2, btnTower3;
    TowerDefense towerDefense;
    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tower_defense);

        DisplayMetrics metrics = new DisplayMetrics(); // find size of the screen
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        towerDefense = new TowerDefense(width, height);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        btnStart = findViewById(R.id.start);
        btnHit = findViewById(R.id.hit);
        btnTower1 = findViewById(R.id.tower1);
        btnTower2 = findViewById(R.id.tower2);
        btnTower3 = findViewById(R.id.tower3);
        GameView gameView = findViewById(R.id.myView);
        if(gameView != null) {
            gameView.setTowerDefense(towerDefense);
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                //btnHit.setVisibility(View.VISIBLE);
                btnTower1.setVisibility(View.VISIBLE);
                btnTower2.setVisibility(View.VISIBLE);
                btnTower3.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                towerDefense.addEnemy();
                break;

            case R.id.hit:
                towerDefense.clicked();
                break;

            case R.id.finish: {
                boolean win = towerDefense.getWin();
                ArrayList<Enemy> enemies = towerDefense.getWave1(); // change to wave3 when implemented
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                if (!win) {
                    builder.setMessage("You have LOST:(!");
                }
                if (enemies.isEmpty() && win) {
                    builder.setMessage("Congratulations! You have WON!!");
                }
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.show();
                break;
            }
            default:
                break;

        }
    }
}

// can i push?



