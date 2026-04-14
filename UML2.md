@startuml
title Observer Pattern - Homework 7

interface GameObserver {
+onEvent(event: GameEvent): void
}

enum GameEventType {
ATTACK_LANDED
HERO_LOW_HP
HERO_DIED
BOSS_PHASE_CHANGED
BOSS_DEFEATED
}

class GameEvent {
-type: GameEventType
-sourceName: String
-value: int
+getType(): GameEventType
+getSourceName(): String
+getValue(): int
}

class EventPublisher {
-observers: List<GameObserver>
+addObserver(observer: GameObserver): void
+notifyObservers(event: GameEvent): void
}

class DungeonBoss {
+publish(event: GameEvent): void
+onEvent(event: GameEvent): void
}

class BattleLogger {
+onEvent(event: GameEvent): void
}

class AchievementTracker {
-attacksLanded: int
-heroDeaths: int
-totalHeroes: int
-unlocked: Set<String>
+onEvent(event: GameEvent): void
}

class PartySupport {
-heroes: List<CombatHero>
-healAmount: int
+onEvent(event: GameEvent): void
}

class HeroStatusMonitor {
-heroes: List<CombatHero>
+onEvent(event: GameEvent): void
}

class LootDropper {
+onEvent(event: GameEvent): void
}

EventPublisher --> GameObserver : notifies
GameObserver --> GameEvent : receives
GameEvent --> GameEventType : contains

DungeonBoss --|> EventPublisher
DungeonBoss ..|> GameObserver
BattleLogger ..|> GameObserver
AchievementTracker ..|> GameObserver
PartySupport ..|> GameObserver
HeroStatusMonitor ..|> GameObserver
LootDropper ..|> GameObserver

@enduml