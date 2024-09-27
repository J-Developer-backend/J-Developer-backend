package com.generate.util;

import java.util.Random;

public class RandomUtil {

    private static final Random RAND = new Random();

    public static int randInt(int min, int max) {
        return RAND.nextInt((max - min) + 1) + min;
    }
}
