package com.tylersuehr.ist446game.game.framework;
import android.view.MotionEvent;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * Hence its name, a screen is what part of the game the user is currently viewing/interacting with.
 */
public abstract class Screen {
    protected final IGamePresenter presenter;
    protected final IGame game;


    public Screen(IGame game, IGamePresenter presenter) {
        this.game = game;
        this.presenter = presenter;
    }

    /**
     * Called after the screen is instantiated.
     */
    public abstract void onInitialize();

    /**
     * Called when the data of the screen should be updated.
     * @param delta Delta time
     */
    public abstract void onUpdate(float delta);

    /**
     * Called when the data of the screen should be drawn.
     * @param p {@link Painter}
     */
    public abstract void onDraw(Painter p);

    /**
     * Called when the user touches any part of the screen.
     * @param e {@link MotionEvent}
     * @param touchX Touched x coordinate
     * @param touchY Touch y coodinate
     * @return True if touch should be consumed
     */
    public abstract boolean onTouch(MotionEvent e, int touchX, int touchY);

    /**
     * Sets the current game's screen.
     * @param newScreen {@link Screen}
     */
    public void setCurrentScreen(Screen newScreen) {
        this.game.setCurrentScreen(newScreen);
    }
}