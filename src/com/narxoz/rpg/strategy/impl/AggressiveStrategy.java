package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;

public class AggressiveStrategy implements CombatStrategy {

    @Override
    public int calculateDamage(int basePower) {
        return (int) Math.round(basePower * 1.40);
    }

    @Override
    public int calculateDefense(int baseDefense) {
        return Math.max(0, (int) Math.round(baseDefense * 0.75));
    }

    @Override
    public String getName() {
        return "Aggressive";
    }
}