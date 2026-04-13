package com.narxoz.rpg.observer.impl;

import com.narxoz.rpg.combatant.CombatHero;
import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.observer.GameObserver;

import java.util.List;

public class HeroStatusMonitor implements GameObserver {

    private final List<CombatHero> heroes;

    public HeroStatusMonitor(List<CombatHero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP || event.getType() == GameEventType.HERO_DIED) {
            System.out.println("[STATUS] Party status:");
            for (CombatHero hero : heroes) {
                String state = hero.isAlive() ? "ALIVE" : "DEAD";
                System.out.println(" - " + hero.getName() + ": " + hero.getHp() + "/" + hero.getMaxHp() + " HP (" + state + ")");
            }
        }
    }
}