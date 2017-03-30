package com.tylersuehr.ist446game.game.models;
import com.tylersuehr.ist446game.game.GameView;
import com.tylersuehr.ist446game.game.Physics;
import com.tylersuehr.ist446game.game.framework.Randomizer;
import com.tylersuehr.ist446game.game.screens.PlayScreen;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/30/2017.
 */
public class Meteor extends Enemy {
    public static final int WIDTH = 169;
    public static final int HEIGHT = 166;
    private final int velocity;


    public Meteor() {
        super(Randomizer.rand(GameView.GAME_WIDTH - Meteor.WIDTH), -(Randomizer.rand(HEIGHT << 1)), WIDTH, HEIGHT);
        this.direction = x > (GameView.GAME_WIDTH >> 1) ? Physics.DIRECTION_LEFT : Physics.DIRECTION_RIGHT;
        this.velocity = (Physics.GRAVITY << 2);
    }

    @Override
    public void update(float delta) {
        y += velocity;

        int slope = (direction == Physics.DIRECTION_LEFT) ? -2 : 2;
        x += velocity / slope;

        if (y >= PlayScreen.G_LEVEL + HEIGHT) {
            this.x = Randomizer.rand(GameView.GAME_WIDTH - WIDTH);
            this.y = -(Randomizer.rand(HEIGHT << 2));
            this.direction = x > (GameView.GAME_WIDTH >> 1)
                    ? Physics.DIRECTION_LEFT : Physics.DIRECTION_RIGHT;
        }

        // Update the bounds
        this.bounds.set((int)x, (int)y, (int)x + WIDTH, (int)y + HEIGHT);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(@Physics.Direction int direction) {
        this.direction = direction;
    }
}