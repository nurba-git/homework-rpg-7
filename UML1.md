@startuml
title Strategy Pattern - Homework 7

interface CombatStrategy {
+calculateDamage(basePower: int): int
+calculateDefense(baseDefense: int): int
+getName(): String
}

class Hero {
-name: String
-hp: int
-maxHp: int
-attackPower: int
-defense: int
+getName(): String
+getHp(): int
+getMaxHp(): int
+getAttackPower(): int
+getDefense(): int
+isAlive(): boolean
+takeDamage(amount: int): void
+heal(amount: int): void
}

class CombatHero {
-strategy: CombatStrategy
-lowHpAnnounced: boolean
+getStrategy(): CombatStrategy
+setStrategy(strategy: CombatStrategy): void
+isLowHpAnnounced(): boolean
+setLowHpAnnounced(value: boolean): void
+getStrategyName(): String
}

class DungeonBoss {
-name: String
-hp: int
-maxHp: int
-attackPower: int
-defense: int
-currentPhase: int
-strategy: CombatStrategy
-phaseOneStrategy: CombatStrategy
-phaseTwoStrategy: CombatStrategy
-phaseThreeStrategy: CombatStrategy
+getStrategy(): CombatStrategy
}

class AggressiveStrategy
class DefensiveStrategy
class BalancedStrategy
class BossMeasuredStrategy
class BossPhaseTwoStrategy
class BossDesperateStrategy

Hero <|-- CombatHero

CombatStrategy <|.. AggressiveStrategy
CombatStrategy <|.. DefensiveStrategy
CombatStrategy <|.. BalancedStrategy
CombatStrategy <|.. BossMeasuredStrategy
CombatStrategy <|.. BossPhaseTwoStrategy
CombatStrategy <|.. BossDesperateStrategy

CombatHero --> CombatStrategy : uses
DungeonBoss --> CombatStrategy : uses

@enduml