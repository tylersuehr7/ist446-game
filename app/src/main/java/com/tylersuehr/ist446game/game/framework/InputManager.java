package com.tylersuehr.ist446game.game.framework;
import android.view.MotionEvent;
import android.view.View;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * Handles every touch event within the bounds of our game.
 */
public final class InputManager implements View.OnTouchListener {
    private final int gameWidth;
    private final int gameHeight;
    private Screen currentScreen;


    public InputManager(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        int scaledX = (int)((e.getX() / v.getWidth()) * gameWidth);
        int scaledY = (int)((e.getY() / v.getHeight()) * gameHeight);
        return currentScreen.onTouch(e, scaledX, scaledY);
    }

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }
}