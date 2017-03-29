package com.tylersuehr.ist446game.game.framework;
import java.util.Random;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This is used for random numbers and to calculate chance.
 */
public final class Randomizer {
    private static Random rnd;


    public static int rand(int upperLimit) {
        if (rnd == null) {
            rnd = new Random();
        }
        return rnd.nextInt(upperLimit);
    }

    public static int rand(int lowerLimit, int upperLimit) {
        if (rnd == null) {
            rnd = new Random();
        }
        return rnd.nextInt((upperLimit - lowerLimit) + lowerLimit);
    }

    public static boolean chance(int prob) {
        return rnd.nextInt(prob) == 1;
    }
}