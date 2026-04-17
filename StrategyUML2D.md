                +----------------------+
                |   CombatStrategy     |
                |----------------------|
                | +calculateDamage()   |
                | +calculateDefense()  |
                | +getName()           |
                +----------+-----------+
                           |
     -----------------------------------------------
     |        |          |        |        |       |
     v        v          v        v        v       v
+------------+ +------------+ +------------+ +------------+ +------------+ +--------------------+
| Aggressive | | Defensive  | | Balanced   | | BossPhase1 | | BossPhase2 | | BossPhase3         |
| Strategy   | | Strategy   | | Strategy   | | Strategy   | | Strategy   | | Strategy           |
+------------+ +------------+ +------------+ +------------+ +------------+ +--------------------+

                (uses)                         (uses)
                  |                               |
                  v                               v
        +------------------+            +----------------------+
        |   CombatHero     |            |     DungeonBoss      |
        |------------------|            |----------------------|
        | -strategy        |            | -strategy            |
        | -lowHpFlag       |            | -currentPhase        |
        +------------------+            +----------------------+
                  ^
                  |
             extends
                  |
        +------------------+
        |      Hero        |
        +------------------+