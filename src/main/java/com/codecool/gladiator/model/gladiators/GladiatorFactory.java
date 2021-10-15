package com.codecool.gladiator.model.gladiators;

import com.codecool.gladiator.util.RandomUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GladiatorFactory {

    private static final int MIN_POINTS = 100;
    private static final int MAX_POINTS = 250;
    private static final int NUMBER_OF_LEVELS = 5;

    private List<String> names;

    public GladiatorFactory(String fileOfNames) {
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileOfNames)).getFile());
            names = Files.readAllLines(file.toPath());
        } catch (IOException | NullPointerException e) {
            System.out.println("Names file not found or corrupted!");
            System.exit(1);
        }
    }

    /**
     * Picks a random name from the file given in the constructor
     *
     * @return gladiator name
     */
    private String getRandomName() {
        return names.get(RandomUtil.randomNumber(names.size()));
    }

    private int getRandomPoints() {
        return RandomUtil.randomNumber(MAX_POINTS - MIN_POINTS) + MIN_POINTS;
    }

    private int getRandomLevel() {
        return RandomUtil.randomNumber(NUMBER_OF_LEVELS) + 1;
    }

    /**
     * Instantiates a new gladiator with random name and type.
     * Creating an Archer, an Assassin, or a Brutal has the same chance,
     * while the chance of creating a Swordsman is the double of the chance of creating an Archer.
     *
     * @return new Gladiator
     */
    public Gladiator generateRandomGladiator() {
        List<Gladiator> gladiators = new ArrayList<>();
        gladiators.add(new Brutal(getRandomName(), getRandomPoints(), getRandomPoints(), getRandomPoints(), getRandomLevel()));
        gladiators.add(new Archer(getRandomName(), getRandomPoints(), getRandomPoints(), getRandomPoints(), getRandomLevel()));
        gladiators.add(new Assassin(getRandomName(), getRandomPoints(), getRandomPoints(), getRandomPoints(), getRandomLevel()));
        gladiators.add(new Swordsman(getRandomName(), getRandomPoints(), getRandomPoints(), getRandomPoints(), getRandomLevel()));
        gladiators.add(new Swordsman(getRandomName(), getRandomPoints(), getRandomPoints(), getRandomPoints(), getRandomLevel()));

        return gladiators.get(RandomUtil.randomNumber(gladiators.size()));
    }
}
