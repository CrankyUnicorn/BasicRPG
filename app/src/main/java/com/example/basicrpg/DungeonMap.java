package com.example.basicrpg;

public class DungeonMap {
    private static final DungeonMap DUNGEON_MAP = new DungeonMap();

    public static DungeonMap GetDungeonMap() {
        return DUNGEON_MAP;
    }

    private static Dungeon currentDungeon = new Dungeon(
            GameSettings.dungeonName,
            GameSettings.dungeonLevels,
            GameSettings.dungeonSections,
            GameSettings.dungeonRooms);

    public static Dungeon GetCurrentDungeon() {return currentDungeon;}

    private DungeonMap() {}
}
