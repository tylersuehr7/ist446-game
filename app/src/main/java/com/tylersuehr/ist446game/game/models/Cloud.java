package com.tylersuehr.ist446game.game.models;
import com.tylersuehr.ist446game.game.GameView;
import com.tylersuehr.ist446game.game.Physics;
import com.tylersuehr.ist446game.game.framework.GameObject;
import com.tylersuehr.ist446game.game.framework.Randomizer;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 */
public class Cloud extends GameObject {
    private static final int WIDTH = 95;
    private static final int HEIGHT = 75;
    private int velocity;


    public Cloud(int scale) {
        super(Randomizer.rand(GameView.GAME_WIDTH - WIDTH), Randomizer.rand(GameView.GAME_HEIGHT / 4), WIDTH * scale, HEIGHT * scale);
        this.velocity = Physics.VELOCITY_VERY_SLOW;

        if (Randomizer.chance(4)) {
            this.y = -y;
        }
    }

    @Override
    public void update(float delta) {
        // Cloud moves downwards to appear player moving up
        y += Physics.GRAVITY - 1; // Clouds are less dense so they're slow
        if (y >= GameView.GAME_HEIGHT) {
            y = -HEIGHT;
        }

        // Make cloud move rightward on screen
        x += velocity;

        // Reset cloud when off the screen
        if (x >= GameView.GAME_WIDTH) {
            x = -getWidth();
        }
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(@Physics.Velocity int velocity) {
        this.velocity = velocity;
    }
}