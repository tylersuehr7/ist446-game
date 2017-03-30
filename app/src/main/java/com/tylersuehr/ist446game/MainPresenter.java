package com.tylersuehr.ist446game;
import com.tylersuehr.ist446game.game.framework.IGamePresenter;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 */
public class MainPresenter extends Presenter<MainPresenter.View> implements IGamePresenter {
    public MainPresenter(View v) {
        super(v);
    }

    @Override
    public void updateStats(int score, boolean isBest, Runnable retryCallback) {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public int getHighestScore() {
        return 0;
    }

    @Override
    public int getAttempts() {
        return 0;
    }


    public interface View {
        void onGameOver(int score, boolean isBest, Runnable callback);
        void onDisplayMsg(String msg);
    }
}