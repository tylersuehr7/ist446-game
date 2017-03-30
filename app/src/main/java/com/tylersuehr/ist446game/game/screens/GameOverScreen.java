package com.tylersuehr.ist446game.game.screens;
import android.view.MotionEvent;
import com.tylersuehr.ist446game.game.GameView;
import com.tylersuehr.ist446game.game.framework.IGame;
import com.tylersuehr.ist446game.game.framework.IGamePresenter;
import com.tylersuehr.ist446game.game.framework.Painter;
import com.tylersuehr.ist446game.game.framework.Screen;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This screen is shown whenever the player loses the game.
 */
public class GameOverScreen extends Screen {
    private final String title;


    public GameOverScreen(IGame game, final IGamePresenter presenter, int score) {
        super(game, presenter);

        // Create the title
        boolean isBest = (score > presenter.getHighestScore());
        this.title = (isBest ? "HIGH SCORE" : "GAME OVER");

        // Update the stats and show GameOverDialog
        presenter.updateStats(score, isBest, new Runnable() {
            @Override
            public void run() {
                setCurrentScreen(new PlayScreen(GameOverScreen.super.game, presenter));
            }
        });
    }

    @Override
    public void onInitialize() {}

    @Override
    public void onUpdate(float delta) {}

    @Override
    public void onDraw(Painter p) {
        final int width = GameView.GAME_WIDTH;
        final int height = GameView.GAME_HEIGHT;

        // Draw background color
//        p.setColor(Color.rgb(255, 145, 0));
//        p.fillRect(0, 0, width, height);

        // Draw title text
        p.drawString(title, p.centerText(title, width), 100);
    }

    @Override
    public boolean onTouch(MotionEvent e, int touchX, int touchY) {
        // TODO: Show dialog like in previous game
        setCurrentScreen(new PlayScreen(game, presenter));
        return true;
    }
}