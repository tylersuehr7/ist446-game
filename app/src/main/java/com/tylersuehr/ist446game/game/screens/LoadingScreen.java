package com.tylersuehr.ist446game.game.screens;
import android.content.Context;
import android.view.MotionEvent;
import com.tylersuehr.ist446game.game.Registry;
import com.tylersuehr.ist446game.game.framework.IGame;
import com.tylersuehr.ist446game.game.framework.IGamePresenter;
import com.tylersuehr.ist446game.game.framework.Painter;
import com.tylersuehr.ist446game.game.framework.Screen;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This is the loading screen. It will appear before the play screen in order to display
 * something simple when loading the game resources.
 */
public class LoadingScreen extends Screen {
    private final Context context;


    public LoadingScreen(IGame game, IGamePresenter presenter, Context context) {
        super(game, presenter);
        this.context = context;
    }

    @Override
    public void onInitialize() {
        // Load the game registry
        Registry.loadResources(context);
    }

    @Override
    public void onUpdate(float delta) {
        setCurrentScreen(new PlayScreen(game, presenter));
    }

    @Override
    public void onDraw(Painter p) {}

    @Override
    public boolean onTouch(MotionEvent e, int touchX, int touchY) {
        return false;
    }
}