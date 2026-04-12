package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;

public class DefensiveStrategy implements CombatStrategy {

    @Override
    public int calculateDamage(int basePower) {
        return Math.max(1, (int) Math.round(basePower * 0.85));
    }

    @Override
    public int calculateDefense(int baseDefense) {
        return (int) Math.round(baseDefense * 1.35);
    }

    @Override
    public String getName() {
        return "Defensive";
    }
}