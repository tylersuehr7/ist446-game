package com.tylersuehr.ist446game.game.models;
import com.tylersuehr.ist446game.game.GameView;
import com.tylersuehr.ist446game.game.Physics;
import com.tylersuehr.ist446game.game.framework.Randomizer;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/30/2017.
 */
public class Bird extends Enemy {
    public static final int WIDTH = 169;
    public static final int HEIGHT = 166;
    private int velX;


    public Bird() {
        super(0, 0, WIDTH, HEIGHT);

        boolean left = Randomizer.chance(6); // 1/6 chance
        this.x = left ? -WIDTH : GameView.GAME_WIDTH + WIDTH;
        this.y = Randomizer.rand((GameView.GAME_HEIGHT >> 2) * 3); // 3/4 chance
        this.direction = left ? Physics.DIRECTION_LEFT : Physics.DIRECTION_RIGHT;
        this.velX = Physics.VELOCITY_NORMAL;
    }

    @Override
    public void update(float delta) {
        // Bird moves downwards to appear player moving up
        y += Physics.GRAVITY;

        // Birds can only move from left to right or right to left
        if (direction == Physics.DIRECTION_LEFT) {
            x -= velX;
            if (x <= -WIDTH) {
                x = GameView.GAME_WIDTH + WIDTH;
                y = Randomizer.rand((GameView.GAME_HEIGHT >> 2) * 3); // 3/4 chance
                direction = Randomizer.chance(6) ? Physics.DIRECTION_RIGHT : Physics.DIRECTION_LEFT;
            }
        } else {
            x += velX;
            if (x >= GameView.GAME_WIDTH + WIDTH) {
                x = -WIDTH;
                y = Randomizer.rand((GameView.GAME_HEIGHT >> 2) * 3); // 3/4 chance
                direction = Randomizer.chance(6) ? Physics.DIRECTION_RIGHT : Physics.DIRECTION_LEFT;
            }
        }

        // Update the bounds (make bounds slightly smaller because of rounding)
        this.bounds.set((int)x, (int)y, (int)x + WIDTH, (int)y + HEIGHT);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }
}