package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;

public class BossDesperateStrategy implements CombatStrategy {

    @Override
    public int calculateDamage(int basePower) {
        return (int) Math.round(basePower * 1.60);
    }

    @Override
    public int calculateDefense(int baseDefense) {
        return Math.max(0, (int) Math.round(baseDefense * 0.55));
    }

    @Override
    public String getName() {
        return "Desperate Phase";
    }
}