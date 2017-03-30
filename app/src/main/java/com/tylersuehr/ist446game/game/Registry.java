package com.tylersuehr.ist446game.game;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import com.tylersuehr.ist446game.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This will store all the pre-loaded game content... such as Bitmaps, Drawables, and audio.
 */
public final class Registry {
    private static AssetManager assets;
    private static SoundPool sounds;
    private static Map<String, Integer> colorCache;

    public static float density;
    public static Drawable cloud;
    public static Drawable player;

    public static void initialize(Context c) {
        Registry.assets = c.getAssets();
        Registry.sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        Registry.colorCache = new HashMap<>();
        Registry.density = c.getResources().getDisplayMetrics().density;
    }

    public static void loadResources(Context c) {
        Registry.player = ContextCompat.getDrawable(c, R.drawable.ic_player_balloon);
        Registry.cloud = ContextCompat.getDrawable(c, R.drawable.ic_cloud);


//        background = loadBitmap("bg.png", false);
//        rightRun = new Animation(
//                new Frame(loadBitmap("rightward_0.png", true), .1f),
//                new Frame(loadBitmap("rightward_1.png", true), .1f),
//                new Frame(loadBitmap("rightward_2.png", true), .1f),
//                new Frame(loadBitmap("rightward_3.png", true), .1f));
//        leftRun = new Animation(
//                new Frame(loadBitmap("leftward_0.png", true), .1f),
//                new Frame(loadBitmap("leftward_1.png", true), .1f),
//                new Frame(loadBitmap("leftward_2.png", true), .1f),
//                new Frame(loadBitmap("leftward_3.png", true), .1f));
//        rocketSound = loadSound("rocket_fire.ogg");
    }

    /**
     * Caches the requested color.
     * @param value Hex decimal color
     * @return Color
     */
    @ColorInt
    public static int color(String value) {
        if (colorCache.containsKey(value)) {
            return colorCache.get(value);
        }
        int color = Color.parseColor(value);
        colorCache.put(value, color);
        return color;
    }

    /**
     * Plays a sound using the given sound id.
     * @param soundId Sound Id
     */
    public static void playSound(int soundId) {
        sounds.play(soundId, 1, 1, 0, 0, 1);
    }

    /**
     * Loads a sound from our assets folder.
     * @param fileName Name of the audio file
     * @return Sound Id
     */
    private static int loadSound(String fileName) {
        try {
            AssetFileDescriptor fd = assets.openFd(fileName);
            return sounds.load(fd, 1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * Loads a Bitmap from our assets folder.
     * @param fileName Name of the image.
     * @param trans True if image should be transparent
     * @return {@link Bitmap}
     */
    private static Bitmap loadBitmap(String fileName, boolean trans) {
        try {
            InputStream is = assets.open(fileName);
            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inPreferredConfig = trans ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            return BitmapFactory.decodeStream(is, null, op);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}