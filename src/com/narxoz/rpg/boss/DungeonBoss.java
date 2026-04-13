package com.narxoz.rpg.boss;

import com.narxoz.rpg.engine.EventPublisher;
import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.observer.GameObserver;
import com.narxoz.rpg.strategy.CombatStrategy;
import com.narxoz.rpg.strategy.impl.BossDesperateStrategy;
import com.narxoz.rpg.strategy.impl.BossMeasuredStrategy;
import com.narxoz.rpg.strategy.impl.BossPhaseTwoStrategy;

public class DungeonBoss extends EventPublisher implements GameObserver {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;

    private int currentPhase;
    private CombatStrategy strategy;

    private final CombatStrategy phaseOneStrategy;
    private final CombatStrategy phaseTwoStrategy;
    private final CombatStrategy phaseThreeStrategy;

    public DungeonBoss(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;

        this.phaseOneStrategy = new BossMeasuredStrategy();
        this.phaseTwoStrategy = new BossPhaseTwoStrategy();
        this.phaseThreeStrategy = new BossDesperateStrategy();

        this.strategy = phaseOneStrategy;
        this.currentPhase = 1;

        addObserver(this);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefense() {
        return defense;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public CombatStrategy getStrategy() {
        return strategy;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void publish(GameEvent event) {
        notifyObservers(event);
    }

    public void takeDamage(int amount, int currentRound) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        int oldHp = hp;
        hp = Math.max(0, hp - amount);

        int oldPercent = (int) Math.ceil((oldHp * 100.0) / maxHp);
        int newPercent = (int) Math.ceil((hp * 100.0) / maxHp);

        if (oldPercent > 60 && newPercent <= 60 && currentPhase < 2) {
            publish(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, 2));
        }

        if (oldPercent > 30 && newPercent <= 30 && currentPhase < 3) {
            publish(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, 3));
        }

        if (hp == 0) {
            publish(new GameEvent(GameEventType.BOSS_DEFEATED, name, currentRound));
        }
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() != GameEventType.BOSS_PHASE_CHANGED) {
            return;
        }

        if (!name.equals(event.getSourceName())) {
            return;
        }

        int newPhase = event.getValue();
        if (newPhase == currentPhase) {
            return;
        }

        currentPhase = newPhase;

        switch (currentPhase) {
            case 2 -> strategy = phaseTwoStrategy;
            case 3 -> strategy = phaseThreeStrategy;
            default -> strategy = phaseOneStrategy;
        }
    }
}