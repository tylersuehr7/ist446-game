package com.tylersuehr.ist446game.game.framework;

/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 */
public interface IGame {
    void startGame();
    void stopGame();
    void setCurrentScreen(Screen screen);
}