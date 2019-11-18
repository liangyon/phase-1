package com.example.userinterface.GameManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.SpaceInvaders.SpaceActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;

public abstract class GameActivity extends AppCompatActivity {
    public Context context;
    private static User user;
    private static boolean ifLost = false;
    private static final Class[] CLASSES =new Class[]{MenuActivity.class, HangManActivity.class, TowerDefenseActivity.class,
            SpaceActivity.class, EndGame.class};

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MenuActivity.class);
        startActivity(intent);
    }

    public void toGame(int saved){
        ifLost = false;
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(context);
        gameBackgroundActivity.execute("quit", user);
        user.getStatTracker().setLevel(saved);
        Intent intent = new Intent(context, CLASSES[saved]);
        startActivity(intent);
    }

    public void goToIntermediate(boolean won){
        GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity(context);

        if (won){
            int num = user.getStatTracker().getLevel()+1;
            user.getStatTracker().setLevel(num);
        }else {
            user.getStatTracker().setLevel(0);
            ifLost = true;
        }
        gameBackgroundActivity.execute("quit", user);
        Intent intent = new Intent(context, NextGame.class);
        startActivity(intent);
    }

    public User getUser(){
        return user;
    }

    public void toMenu(){
        ifLost = false;
        Intent intent = new Intent(context, MenuActivity.class);
        startActivity(intent);
    }

    public static void setUser(User user){
        GameActivity.user = user;
    }

    public void next(View view){
        ifLost = false;
        Intent intent = new Intent(context, CLASSES[user.getStatTracker().getLevel()]);
        startActivity(intent);
    }

    public static boolean isIfLost() {
        return ifLost;
    }
}
