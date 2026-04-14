package com.narxoz.rpg;

import com.narxoz.rpg.boss.DungeonBoss;
import com.narxoz.rpg.combatant.CombatHero;
import com.narxoz.rpg.engine.DungeonEngine;
import com.narxoz.rpg.engine.EncounterResult;
import com.narxoz.rpg.observer.impl.AchievementTracker;
import com.narxoz.rpg.observer.impl.BattleLogger;
import com.narxoz.rpg.observer.impl.HeroStatusMonitor;
import com.narxoz.rpg.observer.impl.LootDropper;
import com.narxoz.rpg.observer.impl.PartySupport;
import com.narxoz.rpg.strategy.impl.AggressiveStrategy;
import com.narxoz.rpg.strategy.impl.BalancedStrategy;
import com.narxoz.rpg.strategy.impl.DefensiveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CombatHero hero1 = new CombatHero("Auron", 140, 26, 8, new AggressiveStrategy());
        CombatHero hero2 = new CombatHero("Selene", 160, 20, 12, new DefensiveStrategy());
        CombatHero hero3 = new CombatHero("Kai", 150, 23, 10, new BalancedStrategy());

        List<CombatHero> heroes = new ArrayList<>();
        heroes.add(hero1);
        heroes.add(hero2);
        heroes.add(hero3);

        DungeonBoss boss = new DungeonBoss("Cursed Warden", 420, 28, 10);

        BattleLogger logger = new BattleLogger();
        AchievementTracker achievements = new AchievementTracker(heroes.size());
        PartySupport support = new PartySupport(heroes, 18);
        HeroStatusMonitor monitor = new HeroStatusMonitor(heroes);
        LootDropper lootDropper = new LootDropper();

        boss.addObserver(logger);
        boss.addObserver(achievements);
        boss.addObserver(support);
        boss.addObserver(monitor);
        boss.addObserver(lootDropper);

        DungeonEngine engine = new DungeonEngine(30);
        EncounterResult result = engine.runEncounter(heroes, boss);

        System.out.println("\n========== FINAL RESULT ==========");
        System.out.println("Heroes won: " + result.isHeroesWon());
        System.out.println("Rounds played: " + result.getRoundsPlayed());
        System.out.println("Surviving heroes: " + result.getSurvivingHeroes());
    }
}