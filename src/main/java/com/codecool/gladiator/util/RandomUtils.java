package com.codecool.gladiator.util;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public int randomNumber(int i) {
        return RANDOM.nextInt(i);
    }
}
