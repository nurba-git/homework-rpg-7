package com.narxoz.rpg.strategy.impl;

import com.narxoz.rpg.strategy.CombatStrategy;

public class BossMeasuredStrategy implements CombatStrategy {

    @Override
    public int calculateDamage(int basePower) {
        return basePower;
    }

    @Override
    public int calculateDefense(int baseDefense) {
        return baseDefense;
    }

    @Override
    public String getName() {
        return "Measured Phase";
    }
}