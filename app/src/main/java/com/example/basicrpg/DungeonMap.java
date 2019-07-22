package com.example.basicrpg;

public class DungeonMap {
    private static final DungeonMap ourInstance = new DungeonMap();

    public static DungeonMap getInstance() {
        return ourInstance;
    }

    public static Dungeon currentDungeon = null;



    private DungeonMap() {


        currentDungeon = new Dungeon(GameSettings.dungeonName,GameSettings.dungeonLevels,GameSettings.dungeonSections,GameSettings.dungeonRooms);
    }
}
