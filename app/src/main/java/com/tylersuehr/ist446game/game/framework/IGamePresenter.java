package com.tylersuehr.ist446game.game.framework;

/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 */
public interface IGamePresenter {
    void updateStats(int score, boolean isBest, Runnable retryCallback);
    String getUsername();
    int getHighestScore();
    int getAttempts();
}
