package com.codecool.gladiator.model.gladiators;

public abstract class Gladiator {

    private final String name;
    private final int baseHp;
    private final int baseSp;
    private final int baseDex;
    private final int level;
    private int currentHp;

    /**
     * Constructor for Gladiators
     *
     * @param name    the gladiator's name
     * @param baseHp  the gladiator's base Health Points
     * @param baseSp  the gladiator's base Strength Points
     * @param baseDex the gladiator's base Dexterity Points
     * @param level   the gladiator's starting Level
     */
    public Gladiator(String name, int baseHp, int baseSp, int baseDex, int level) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseSp = baseSp;
        this.baseDex = baseDex;
        this.level = level;
        currentHp = getBaseHp();
    }

    /**
     * @return HP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getHpMultiplier();

    /**
     * @return SP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getSpMultiplier();

    /**
     * @return DEX multiplier of the gladiator subclass
     */
    protected abstract Multiplier getDexMultiplier();

    public int getGladiatorsMaxHp() {
        return (int) getHpMultiplier().getValue() * level * baseHp;
    }

    public int getGladiatorsMaxSp() {
        return (int) getSpMultiplier().getValue() * level * baseSp;
    }

    public int getGladiatorsMaxDex() {
        return (int) getDexMultiplier().getValue() * level * baseDex;
    }

    public int getLevel() {
        return level;
    }

    /**
     * @return Gladiator's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the full name of the gladiator
     * assembled by the subtype and the name
     * (e.g. "Brutal Brutus" or "Archer Leo")
     *
     * @return the full name
     */
    public String getFullName() {
        return this.getClass().getSimpleName() + " " + name;
    }

    public void decreaseHpBy(int damage) {
        setCurrentHp(getCurrentHp() - damage);
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public boolean isNotDead() {
        return getCurrentHp() > 0;
    }

    public boolean isDead() {
        return getCurrentHp() <= 0;
    }

    public void healUp() {
        currentHp = getGladiatorsMaxHp();
    }

    public enum Multiplier {
        Low(0.75),
        Medium(1.0),
        High(1.25);

        private final double value;

        Multiplier(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }
}
