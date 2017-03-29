package com.tylersuehr.ist446game.game.framework;
import android.graphics.Rect;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * All game objects must inherit this... contains basic coordinates and hit rect.
 */
public abstract class GameObject {
    protected Rect bounds;
    protected float x;
    protected float y;


    public GameObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.bounds = new Rect((int)x, (int)y, (int)x + width, (int)y + height);
    }

    public abstract void update(float delta);

    public Rect getBounds() {
        return bounds;
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return bounds.width();
    }

    public void setWidth(int width) {
        this.bounds.right = (int)x + width;
    }

    public int getHeight() {
        return bounds.height();
    }

    public void setHeight(int height) {
        this.bounds.bottom = (int)y + height;
    }
}