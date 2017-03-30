package com.tylersuehr.ist446game.game.models;
import android.graphics.Rect;
import com.tylersuehr.ist446game.game.Physics;
import com.tylersuehr.ist446game.game.framework.GameObject;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/30/2017.
 */
public abstract class Enemy extends GameObject {
    @Physics.Direction
    protected int direction;


    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean checkPlayerCollide(Player p) {
        // Allow some leeway
        Rect a = bounds;
        Rect b = p.getBounds();
        p.setAlive(a.left + 20 < b.right && b.left < a.right - 20 && a.top + 20 < b.bottom && b.top < a.bottom - 20);
        return p.isAlive();
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(@Physics.Direction int direction) {
        this.direction = direction;
    }
}