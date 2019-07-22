package com.example.basicrpg;

import com.example.basicrpg.Util;

public class DungeonNameGenerator {
	
	private static final String[] titleName = {"The"};

    private static final String[] prefixName = { "Rocky", "", "Dark", "", "Sunless", "Burning", "Hallow", "Bloody", "Standing", "Lonesome","Crying"};



    private static final String[] dungeonName = {"Keep","Cave","Spire","Citadel" ,"Fortress","Mine","Crypt","Ruin","Factory"};

    private static final String[] suffixName = {"Of Shadow","","","Of Fury","Of The Vampire","Of Evil","Of Doom","Of Despair","Of Pain","Of Tears"};


    private static final String[] levelName =  {"Creek","Path","","Mother","Stone","Hall","Crib","Stairs"};


    private static final String[] roomNamePrefix = {"Mossy","Cold","Smelly","Dusty","Dark","Claustrophobic","Horrid","Blood Splattered"};
    private static final String[] roomName = {"Chamber","Closet","Pool","Pit","Corridor","Small Chamber","Hallway","Stairway","Store"};



    private static final String[] trapNamePrefix = { "Dark", "Bloody", "Dreadic", "Sharp", "Rusty","Poisoned"};
    private static final String[] trapName = {"Spring","Dead","Bear","Switch","Rat"};
    private static final String[] trapNameSuffix = {"Trap","Pit","Deadfall","Loop","Spike"};



    public static String GenerateDungeonName(){

        String output = titleName[0] + " " +
						prefixName[Util.GenerateNumberBetween(0,prefixName.length)] + " " +
                        dungeonName [Util.GenerateNumberBetween(0,dungeonName.length)] + " " +
                        suffixName [Util.GenerateNumberBetween(0,suffixName.length)];
        return output;
    }

    public static String GenerateLevelName(){
        String output = prefixName[Util.GenerateNumberBetween(0,prefixName.length)] + " " +
                        levelName [Util.GenerateNumberBetween(0,levelName.length)];
        return output;
    }

    public static String GenerateRoomName(){
        String output = roomNamePrefix[Util.GenerateNumberBetween(0,roomNamePrefix.length)] + " " +
                        roomName [Util.GenerateNumberBetween(0,roomName.length)];
        return output;
    }

    public static String GenerateTrapName(){

        String output =trapNamePrefix[Util.GenerateNumberBetween(0,trapNamePrefix.length)]+" "+
                trapName[Util.GenerateNumberBetween(0,trapName.length)]+" "+
                trapNameSuffix[Util.GenerateNumberBetween(0,trapNameSuffix.length)];

                return output;
    }


    public static String GenerateRoomDescription(){

        String output ="This is the room description please create develop this";

        return output;
    }

}
