package com.example.userinterface.GameManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userinterface.GameManager.HangMan.HangManActivity;
import com.example.userinterface.GameManager.SpaceInvaders.SpaceActivity;
import com.example.userinterface.GameManager.TowerDefense.TowerDefenseActivity;

public abstract class GameActivity extends AppCompatActivity {
    private static final Class[] CLASSES = new Class[]{MenuActivity.class, HangManActivity.class, TowerDefenseActivity.class,
            SpaceActivity.class, EndGame.class};
    private static UserDAO user = null;
    private static boolean ifLost = false;
    public Context context;

    public static boolean isIfLost() {
        return ifLost;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MenuActivity.class);
        startActivity(intent);
    }

    public void toGame(int saved, boolean practiceMode) {
        ifLost = false;
        if (!practiceMode) {

            GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity();
            user.get().getStatTracker().setLevel(saved);
            gameBackgroundActivity.execute("quit", user.get());
        }

        Intent intent = new Intent(context, CLASSES[saved]);
        Log.d("message", "this is the boolean in to game " + practiceMode);
        intent.putExtra("practice", practiceMode);
        startActivity(intent);
    }

    public void goToIntermediate(boolean won, boolean practiceMode) {
        if (!won) {
            ifLost = true;
        }
        if (!practiceMode) {
            GameBackgroundActivity gameBackgroundActivity = new GameBackgroundActivity();
            if (won) {
                int num = user.get().getStatTracker().getLevel() + 1;
                user.get().getStatTracker().setLevel(num);
            } else {
                user.get().getStatTracker().setCurrScore(0);
                user.get().getStatTracker().setLevel(0);
                ifLost = true;
            }
            gameBackgroundActivity.execute("quit", user.get());
        }

        Intent intent = new Intent(context, NextGame.class);
        intent.putExtra("practice", practiceMode);
        Log.d("message", "this is the boolean at to game in go to inter " + practiceMode);
        startActivity(intent);

    }

    public User getUser() {
        return user.get();
    }

    public static void setUser(UserDAO user) {
        if (GameActivity.user == null) {
            GameActivity.user = user;
        }

    }

    public void toMenu() {
        ifLost = false;
        Intent intent = new Intent(context, MenuActivity.class);
        startActivity(intent);
    }

    public void deleteUser() {
        user = null;
    }

    public void next(View view) {
        ifLost = false;
        Intent intent = new Intent(context, CLASSES[user.get().getStatTracker().getLevel()]);
        startActivity(intent);
    }
}
