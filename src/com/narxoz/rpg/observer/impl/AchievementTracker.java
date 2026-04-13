package com.narxoz.rpg.observer.impl;

import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.observer.GameObserver;

import java.util.HashSet;
import java.util.Set;

public class AchievementTracker implements GameObserver {

    private int attacksLanded = 0;
    private int heroDeaths = 0;
    private final int totalHeroes;
    private final Set<String> unlocked = new HashSet<>();

    public AchievementTracker(int totalHeroes) {
        this.totalHeroes = totalHeroes;
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.ATTACK_LANDED) {
            attacksLanded++;

            unlock("First Blood", attacksLanded == 1);
            unlock("Relentless", attacksLanded >= 10);
        }

        if (event.getType() == GameEventType.HERO_DIED) {
            heroDeaths++;
            unlock("The Cost of Victory", heroDeaths >= 1);
        }

        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            unlock("Boss Slayer", true);
            unlock("No Man Left Behind", heroDeaths == 0 && totalHeroes > 0);
            unlock("Last Stand", heroDeaths == totalHeroes - 1 && totalHeroes > 1);
        }
    }

    private void unlock(String title, boolean condition) {
        if (condition && unlocked.add(title)) {
            System.out.println("[ACHIEVEMENT] Unlocked: " + title);
        }
    }
}