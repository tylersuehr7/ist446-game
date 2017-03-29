package com.tylersuehr.ist446game.game.framework;
import android.graphics.Bitmap;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * Represents a Sprite frame for an image.
 */
public class Frame {
    private double duration;
    private Bitmap image;


    public Frame(Bitmap img, double duration) {
        this.image = img;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public Bitmap getImage() {
        return image;
    }
}