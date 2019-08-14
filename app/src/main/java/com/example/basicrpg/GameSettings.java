package com.example.basicrpg;

public class GameSettings {

    public static final int TRAP_TYPES = 5;
    public static final int MONSTER_TYPES = 7;
    public static final int TREASURE_TYPES = 8;
	
    public static final int ITEM_TYPES = 8;

    public static final int ROOM_TYPES = 100;

    public static final int DUNGEON_LEVELS = 3;
    public static final int DUNGEON_SECTIONS = 7;
    public static final int DUNGEON_ROOMS = 30;

    public static final String DUNGEON_NAME = "";

    public static final int ROOM_MAX_EXITS = 4; //never less then 3 and directly correlated to buttons
	
	public static final int PLAYER_MAX_HEALTH =100;
	public static final int PLAYER_HEALTH =100;
	
	public static final int PLAYER_MAX_SANITY =100;
	public static final int PLAYER_SANITY =100;
	
	public static final int PLAYER_DAMAGE = 4;
	public static final int PLAYER_ARMOR = 2;
	public static final int PLAYER_HIT_DODGE =3;

	public static final String[] PLAYER_ROOM_ACTIONS = {"No Actions",
	"Fight Foe","Investigate","Disarm Trap","Open Treasure","Journal"};
}
