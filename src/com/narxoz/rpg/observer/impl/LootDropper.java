package com.narxoz.rpg.observer.impl;

import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.observer.GameObserver;

import java.util.Random;

public class LootDropper implements GameObserver {

    private final Random random = new Random();

    private final String[] phaseTwoLoot = {
            "Iron Rune",
            "Battle Talisman",
            "Minor Soul Gem"
    };

    private final String[] phaseThreeLoot = {
            "Cursed Fang",
            "Ancient Relic",
            "Bloodstone Charm"
    };

    private final String[] bossLoot = {
            "Legendary Dungeon Core",
            "Crown of the Fallen King",
            "Shadowblade of Ruin"
    };

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED) {
            String item = event.getValue() == 2
                    ? phaseTwoLoot[random.nextInt(phaseTwoLoot.length)]
                    : phaseThreeLoot[random.nextInt(phaseThreeLoot.length)];

            System.out.println("[LOOT] Phase reward dropped: " + item);
        }

        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            String item = bossLoot[random.nextInt(bossLoot.length)];
            System.out.println("[LOOT] Final boss reward dropped: " + item);
        }
    }
}