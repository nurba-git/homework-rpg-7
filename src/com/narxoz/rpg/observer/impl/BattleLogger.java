package com.narxoz.rpg.observer.impl;

import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameObserver;

public class BattleLogger implements GameObserver {

    @Override
    public void onEvent(GameEvent event) {
        switch (event.getType()) {
            case ATTACK_LANDED ->
                    System.out.println("[LOG] " + event.getSourceName() + " landed an attack for " + event.getValue() + " damage.");
            case HERO_LOW_HP ->
                    System.out.println("[LOG] " + event.getSourceName() + " is in critical condition! HP: " + event.getValue());
            case HERO_DIED ->
                    System.out.println("[LOG] " + event.getSourceName() + " has fallen.");
            case BOSS_PHASE_CHANGED ->
                    System.out.println("[LOG] Boss phase changed to Phase " + event.getValue() + ".");
            case BOSS_DEFEATED ->
                    System.out.println("[LOG] Boss defeated after " + event.getValue() + " rounds.");
        }
    }
}