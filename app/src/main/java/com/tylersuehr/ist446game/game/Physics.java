package com.tylersuehr.ist446game.game;
import android.support.annotation.IntDef;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This is a contract that holds all our definite physics values.
 */
public final class Physics {
    public static final int VELOCITY_NONE = 0;
    public static final int VELOCITY_SLOW = 1;
    public static final int VELOCITY_NORMAL = 2;
    public static final int VELOCITY_FAST = 4;
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;


    @IntDef(value = { VELOCITY_NONE, VELOCITY_SLOW, VELOCITY_NORMAL, VELOCITY_FAST })
    public @interface Velocity {}

    @IntDef(value = { DIRECTION_LEFT, DIRECTION_RIGHT })
    public @interface Direction {}
}