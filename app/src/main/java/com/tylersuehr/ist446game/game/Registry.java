package com.tylersuehr.ist446game.game;
import android.content.Context;
import android.content.res.AssetManager;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This will store all the pre-loaded game content... such as Bitmaps, Drawables, and audio.
 */
public final class Registry {
    private static AssetManager assets;


    public static void initialize(Context c) {
        Registry.assets = c.getAssets();
    }
}