package com.narxoz.rpg.engine;

import com.narxoz.rpg.boss.DungeonBoss;
import com.narxoz.rpg.combatant.CombatHero;
import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.strategy.impl.AggressiveStrategy;

import java.util.List;

public class DungeonEngine {

    private final int maxRounds;

    public DungeonEngine(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public EncounterResult runEncounter(List<CombatHero> heroes, DungeonBoss boss) {
        int round = 0;
        boolean switchedHeroStrategy = false;

        while (round < maxRounds && boss.isAlive() && countLivingHeroes(heroes) > 0) {
            round++;
            System.out.println("\n========== ROUND " + round + " ==========");
            System.out.println("Boss HP: " + boss.getHp() + "/" + boss.getMaxHp()
                    + " | Phase " + boss.getCurrentPhase()
                    + " | Strategy: " + boss.getStrategy().getName());

            if (!switchedHeroStrategy && round == 3) {
                for (CombatHero hero : heroes) {
                    if (hero.isAlive()) {
                        hero.setStrategy(new AggressiveStrategy());
                        System.out.println("[STRATEGY] " + hero.getName()
                                + " switches to " + hero.getStrategy().getName() + " strategy!");
                        switchedHeroStrategy = true;
                        break;
                    }
                }
            }

            for (CombatHero hero : heroes) {
                if (!hero.isAlive() || !boss.isAlive()) {
                    continue;
                }

                int attackValue = hero.getStrategy().calculateDamage(hero.getAttackPower());
                int defendValue = boss.getStrategy().calculateDefense(boss.getDefense());
                int damage = Math.max(1, attackValue - defendValue);

                System.out.println(hero.getName() + " [" + hero.getStrategyName() + "] attacks boss for " + damage + " damage.");
                boss.publish(new GameEvent(GameEventType.ATTACK_LANDED, hero.getName(), damage));
                boss.takeDamage(damage, round);

                if (boss.isAlive()) {
                    System.out.println("Boss HP now: " + boss.getHp() + "/" + boss.getMaxHp()
                            + " | Strategy: " + boss.getStrategy().getName());
                }
            }

            if (!boss.isAlive()) {
                break;
            }

            for (CombatHero hero : heroes) {
                if (!hero.isAlive()) {
                    continue;
                }

                int attackValue = boss.getStrategy().calculateDamage(boss.getAttackPower());
                int defendValue = hero.getStrategy().calculateDefense(hero.getDefense());
                int damage = Math.max(1, attackValue - defendValue);

                System.out.println("Boss [" + boss.getStrategy().getName() + "] attacks "
                        + hero.getName() + " for " + damage + " damage.");

                hero.takeDamage(damage);
                boss.publish(new GameEvent(GameEventType.ATTACK_LANDED, boss.getName(), damage));

                if (hero.isAlive() && !hero.isLowHpAnnounced()
                        && hero.getHp() > 0
                        && hero.getHp() <= (int) Math.floor(hero.getMaxHp() * 0.30)) {
                    hero.setLowHpAnnounced(true);
                    boss.publish(new GameEvent(GameEventType.HERO_LOW_HP, hero.getName(), hero.getHp()));
                }

                if (!hero.isAlive()) {
                    boss.publish(new GameEvent(GameEventType.HERO_DIED, hero.getName(), 0));
                }
            }
        }

        boolean heroesWon = boss.getHp() == 0;
        int survivingHeroes = countLivingHeroes(heroes);

        return new EncounterResult(heroesWon, round, survivingHeroes);
    }

    private int countLivingHeroes(List<CombatHero> heroes) {
        int count = 0;
        for (CombatHero hero : heroes) {
            if (hero.isAlive()) {
                count++;
            }
        }
        return count;
    }
}