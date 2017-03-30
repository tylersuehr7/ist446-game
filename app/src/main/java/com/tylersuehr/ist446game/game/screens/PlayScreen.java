package com.tylersuehr.ist446game.game.screens;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.tylersuehr.ist446game.game.GameView;
import com.tylersuehr.ist446game.game.Physics;
import com.tylersuehr.ist446game.game.Registry;
import com.tylersuehr.ist446game.game.framework.IGame;
import com.tylersuehr.ist446game.game.framework.IGamePresenter;
import com.tylersuehr.ist446game.game.framework.Painter;
import com.tylersuehr.ist446game.game.framework.Screen;
import com.tylersuehr.ist446game.game.models.Bird;
import com.tylersuehr.ist446game.game.models.Cloud;
import com.tylersuehr.ist446game.game.models.Enemy;
import com.tylersuehr.ist446game.game.models.Meteor;
import com.tylersuehr.ist446game.game.models.Player;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This is the screen where the user sees and interacts with the game.
 * Game dimensions: 450x900
 */
public class PlayScreen extends Screen {
    private static final boolean drawBounds = true;

    private static final int MAX_WIDTH = GameView.GAME_WIDTH;
    private static final int MAX_HEIGHT = GameView.GAME_HEIGHT;
    public static final int G_HEIGHT = 200;
    public static final int G_LEVEL = MAX_HEIGHT - G_HEIGHT;
    private boolean pressed = false;

    // In-game objects
    private Cloud[] clouds;
    private Enemy[] enemies;
    private Player player;

    private int distance;


    public PlayScreen(IGame game, IGamePresenter presenter) {
        super(game, presenter);
    }

    @Override
    public void onInitialize() {
        // Initialize player
        this.player = new Player((MAX_WIDTH >> 1) - (Player.WIDTH >> 1), G_LEVEL - Player.HEIGHT);

        // Initialize all enemies
        this.enemies = new Enemy[4];
        this.enemies[0] = new Meteor();
        this.enemies[1] = new Bird();
        this.enemies[2] = new Bird();
        this.enemies[3] = new Bird();

        // Initialize all clouds
        this.clouds = new Cloud[5];
        this.clouds[0] = new Cloud(1);
        this.clouds[1] = new Cloud(2);
        this.clouds[2] = new Cloud(3);
        this.clouds[3] = new Cloud(2);
        this.clouds[4] = new Cloud(1);

        this.distance = 0;
    }

    @Override
    public void onUpdate(float delta) {
        this.distance += 1;

        // Update the player
        player.update(delta);

        // Update all the enemies
        for (Enemy enemy : enemies) {
            enemy.update(delta);

            // Check for a player collision
            if (enemy.checkPlayerCollide(player)) {
                setCurrentScreen(new GameOverScreen(game, presenter, actualDistance()));
                return;
            }
        }

        // Update all the clouds
        for (Cloud cloud : clouds) {
            cloud.update(delta);
        }
    }

    @Override
    public void onDraw(Painter p) {
        drawBackground(p);
        drawClouds(p);
        drawPlayer(p);
        drawEnemies(p);

        // Draw the distance score in top left
        p.setColor(Registry.color("#FAFAFA"));
        p.setTextSize(40f);
        p.drawString("Distance: " + actualDistance(), 50, 80);
        p.drawString("Level: 1", 50, 160);
    }

    @Override
    public boolean onTouch(MotionEvent e, int touchX, int touchY) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                break;
            case MotionEvent.ACTION_UP:
                if (pressed) {
                    player.toggleDirection();
                    pressed = false;
                }
                break;
        }
        return true;
    }

    private void drawBackground(Painter p) {
        // Draw the entire fullscreen background
        p.setColor(Registry.color("#9C27B0")); // Purple 500
        p.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);

        // Draw the ground
        if (distance <= G_HEIGHT) {
            p.setColor(Registry.color("#4A148C")); // Purple 900
            p.fillRect(0, G_LEVEL + distance, MAX_WIDTH, MAX_HEIGHT + G_HEIGHT);
        }
    }

    private void drawClouds(Painter p) {
        for (Cloud cloud : clouds) {
            p.drawGameObject(Registry.cloud, cloud);
        }
    }

    private void drawPlayer(Painter p) {
        p.drawGameObject(Registry.player, player);

        if (drawBounds) { // DEBUGGING
            Rect bounds = player.getBounds();
            p.setColor(Registry.color("#AA000000"));
            p.fillRect(bounds.left, bounds.top, bounds.right, bounds.bottom);
        }
    }

    private void drawEnemies(Painter p) {
        for (Enemy enemy : enemies) {
            Drawable dr;
            if (enemy instanceof Bird) {
                dr = (enemy.getDirection() == Physics.DIRECTION_LEFT)
                        ? Registry.bird[0] : Registry.bird[1];
            } else {
                dr = (enemy.getDirection() == Physics.DIRECTION_LEFT)
                        ? Registry.meteor[0] : Registry.meteor[1];
            }
            p.drawGameObject(dr, enemy);

            if (drawBounds) { // DEBUGGING
                Rect bounds = enemy.getBounds();
                p.setColor(Registry.color("#AA000000"));
                p.fillRect(bounds.left, bounds.top, bounds.right, bounds.bottom);
            }
        }
    }

    private int actualDistance() {
        return distance / 60;
    }
}