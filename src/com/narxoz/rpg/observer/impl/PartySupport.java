package com.narxoz.rpg.observer.impl;

import com.narxoz.rpg.combatant.CombatHero;
import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.observer.GameObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartySupport implements GameObserver {

    private final List<CombatHero> heroes;
    private final Random random = new Random();
    private final int healAmount;

    public PartySupport(List<CombatHero> heroes, int healAmount) {
        this.heroes = heroes;
        this.healAmount = healAmount;
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() != GameEventType.HERO_LOW_HP) {
            return;
        }

        List<CombatHero> candidates = new ArrayList<>();
        for (CombatHero hero : heroes) {
            if (hero.isAlive() && !hero.getName().equals(event.getSourceName())) {
                candidates.add(hero);
            }
        }

        CombatHero target;
        if (candidates.isEmpty()) {
            target = findHeroByName(event.getSourceName());
            if (target == null || !target.isAlive()) {
                return;
            }
        } else {
            target = candidates.get(random.nextInt(candidates.size()));
        }

        target.heal(healAmount);
        System.out.println("[SUPPORT] The party support heals " + target.getName()
                + " for " + healAmount + " HP. Current HP: " + target.getHp());
    }

    private CombatHero findHeroByName(String name) {
        for (CombatHero hero : heroes) {
            if (hero.getName().equals(name)) {
                return hero;
            }
        }
        return null;
    }
}