package com.tylersuehr.ist446game.game.models;
import com.tylersuehr.ist446game.game.GameView;
import com.tylersuehr.ist446game.game.Physics;
import com.tylersuehr.ist446game.game.framework.GameObject;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 */
public class Player extends GameObject {
    public static final int WIDTH = 217;
    public static final int HEIGHT = 396;

    private boolean alive;
    private int direction;
    private int velX;


    public Player(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        this.alive = true;
        this.velX = Physics.VELOCITY_NORMAL;
        this.direction = Physics.DIRECTION_LEFT;
    }

    @Override
    public void update(float delta) {
        if (direction == Physics.DIRECTION_LEFT) {
            x -= velX;
            if (x <= -(WIDTH - (WIDTH >> 2))) {
                x = GameView.GAME_WIDTH;
            }
        } else {
            x += velX;
            if (x >= GameView.GAME_WIDTH) {
                x = -(WIDTH - (WIDTH >> 2));
            }
        }

        // Update the bounds
        this.bounds.set((int)x, (int)y, (int)x + WIDTH, (int)y + HEIGHT);
    }

    public void toggleDirection() {
        if (direction == Physics.DIRECTION_LEFT) {
            this.direction = Physics.DIRECTION_RIGHT;
        } else {
            this.direction = Physics.DIRECTION_LEFT;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(@Physics.Direction int direction) {
        this.direction = direction;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}