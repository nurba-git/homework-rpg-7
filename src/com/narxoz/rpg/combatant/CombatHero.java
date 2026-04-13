package com.narxoz.rpg.combatant;

import com.narxoz.rpg.strategy.CombatStrategy;

public class CombatHero extends Hero {

    private CombatStrategy strategy;
    private boolean lowHpAnnounced;

    public CombatHero(String name, int hp, int attackPower, int defense, CombatStrategy strategy) {
        super(name, hp, attackPower, defense);
        if (strategy == null) {
            throw new IllegalArgumentException("Combat strategy cannot be null");
        }
        this.strategy = strategy;
        this.lowHpAnnounced = false;
    }

    public CombatStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CombatStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Combat strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public boolean isLowHpAnnounced() {
        return lowHpAnnounced;
    }

    public void setLowHpAnnounced(boolean lowHpAnnounced) {
        this.lowHpAnnounced = lowHpAnnounced;
    }

    public String getStrategyName() {
        return strategy.getName();
    }
}