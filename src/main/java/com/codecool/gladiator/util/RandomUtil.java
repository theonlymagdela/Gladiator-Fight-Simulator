package com.codecool.gladiator.util;

import java.util.Random;

public class RandomUtil {

    private static final Random RANDOM = new Random();

    public static int randomNumber(int i) {
        return RANDOM.nextInt(i);
    }

    public static double randomDouble() {
        return RANDOM.nextDouble();
    }
}
