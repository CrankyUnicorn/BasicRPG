package com.example.basicrpg;

import com.example.basicrpg.Util;

public class DungeonNameGenerator {
	
	private static final String[] titleName = {"The"};

    private static final String[] prefixName = { "Rocky","Cursed", "Black", "Dark", "Murky","Somber","Obscure",
            "Tenebrous","Caliginous","Lurid","Sooty","Dim", "Sunless", "Burning", "Hallow", "Bloody", "Standing", "Lonesome","Crying"};



    private static final String[] dungeonName = {"Keep","Manor","Cave","Spire","Citadel","Fortress","Mine","Crypt","Ruin","Factory","Church","Cemetery"};

    private static final String[] suffixName = {"Of Shadow","Of Fury","Of The Vampire","Of Evil","Of Doom","Of Despair",
            "Of Pain","Of Tears","Of Whispers","Of Misfortune","Of Sins","Of The Spider", "Of Lost Souls", "Of Agony"};


    private static final String[] levelName =  {"Creek","Path","Mothers","Stone","Hall","Crib","Stairs","Lair","Tunnel","Fathers"};


    private static final String[] roomNamePrefix = {"Mossy","Dim","Cold","Smelly","Dusty","Dark","Claustrophobic","Horrid","Blood Splattered","Burned"};
    private static final String[] roomName = {"Chamber","Closet","Pool","Pit","Corridor","Small Chamber","Hallway","Stairway","Store","Room","Crypt"};



    private static final String[] trapNamePrefix = { "Dark", "Bloody", "Dreadful", "Diseased", "Sharp","Unpleasant","Machiavellian","Rude", "Rusty","Poisoned","Sadistic","Horrific","Distasteful"};
    private static final String[] trapName = {"","","","Spring","Dead","Bear","Switch","Rat","Snake","Flame",};
    private static final String[] trapNameSuffix = {"Trap","Trap","Trap","Pit","Deadfall","Loop","Spike","Device","Bolt Shooter","Blade","Vapor","Gas","Embrace","Bone Crusher","Saw","Flamethrower"};


    private static final String[] monsterNamePrefix ={"Shadowy","Dark","Misfortuned"};
    private static final String[] monsterName ={"Vulture","Shape","Stalker","Soul"};


    private static final String[] treasureNamePrefix = {"Rotting","Moldy","Broken"};
    private static final String[] treasureName = {"Hooden Box","Hooden Keg"};


    private static final String[] treasureDescription = {"Something long forgotten","No one will miss this","One man trash is other man treasure"};

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

        String output ="Just another abandoned room. Decay took over the halls, the air is heavy with the smell of rotten wood and the feeling of dread";

        return output;
    }

    public static String GenerateMonsterName(){

        String output =monsterNamePrefix[Util.GenerateNumberBetween(0,monsterNamePrefix.length)]+" "+
                        monsterName[Util.GenerateNumberBetween(0,monsterName.length)];

        return output;
    }

    public static String GenerateMonsterDescription(){

        String output ="Another damned soul lost in this world";

        return output;
    }


    public static String GenerateTreasureName(){

        String output = treasureNamePrefix[Util.GenerateNumberBetween(0,treasureNamePrefix.length)] + " " +
                        treasureName [Util.GenerateNumberBetween(0,treasureName.length)];
        return output;
    }

    public static String GenerateTreasureDescription(){

        String output = treasureDescription [Util.GenerateNumberBetween(0,treasureDescription.length)];
        return output;
    }

    public static String GenerateItemName(){

        String output ="item name";

        return output;
    }

    public static String GenerateItemDescription(){

        String output ="item description";

        return output;
    }



}
