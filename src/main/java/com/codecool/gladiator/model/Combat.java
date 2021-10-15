package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

    private final Gladiator gladiator1;
    private final Gladiator gladiator2;

    private final List<String> combatLog;

    public Combat(Contestants contestants) {
        this.gladiator1 = contestants.gladiator1;
        this.gladiator2 = contestants.gladiator2;
        this.combatLog = new ArrayList<>();
    }

    /**
     * Simulates the combat and returns the winner.
     * If one of the opponents is null, the winner is the one that is not null
     * If both of the opponents are null, the return value is null
     *
     * @return winner of combat
     */
    public Gladiator simulate() {
        Gladiator attacker = gladiator1;
        Gladiator defender = gladiator2;
        Gladiator winner = null;

        if (gladiator1 == null && gladiator2 == null) {
            return null;
        } else if (gladiator1 == null) {
            return gladiator2;
        } else if (gladiator2 == null) {
            return gladiator1;
        }

        do {
            combatLog.add(attack(attacker, defender));
            if (attacker.isDead()) {
                defender.healUp();
                winner = defender;
                break;
            }
            if (defender.isDead()) {
                attacker.healUp();
                winner = attacker;
                break;
            }
            attacker = attackerSwitch(attacker);
            defender = defenderSwitch(defender);
        } while (attacker.isNotDead() || defender.isNotDead());

        return winner;
    }

    public String attack(Gladiator attacker, Gladiator defender) {
        if (chanceOfHit(attacker)) {
            int damage = (int) (attacker.getGladiatorsMaxSp() * randomInRange());
            defender.decreaseHpBy(damage);
            return String.format("\n%s deals %s damage", attacker.getFullName(), damage);
        }
        return String.format("\n%s missed", attacker.getFullName());
    }

    public boolean chanceOfHit(Gladiator attacker) {
        int value;

        if (attacker == gladiator1) {
            value = gladiator1.getGladiatorsMaxDex() - gladiator2.getGladiatorsMaxDex();
        } else {
            value = gladiator2.getGladiatorsMaxDex() - gladiator1.getGladiatorsMaxDex();
        }

        return RandomUtil.randomNumber(101) < clamp(value);
    }

    public int clamp(int val) {
        if (val <= 10) {
            return 10;
        }
        if (val >= 100) {
            return 100;
        }
        return val;
    }

    public double randomInRange() {
        double rangeMin = 0.1;
        double rangeMax = 0.5;

        return rangeMin + ((rangeMax - rangeMin) * RandomUtil.randomDouble());
    }

    public Gladiator attackerSwitch(Gladiator attacker) {
        return attacker == gladiator1 ? gladiator2: gladiator1;
    }

    public Gladiator defenderSwitch(Gladiator defender) {
        return defender == gladiator2 ? gladiator1: gladiator2;
    }

    public Gladiator getGladiator1() {
        return gladiator1;
    }

    public Gladiator getGladiator2() {
        return gladiator2;
    }

    public String getCombatLog(String separator) {
        return String.join(separator, combatLog);
    }
}
