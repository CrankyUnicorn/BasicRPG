package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrapsList {

    public List<RoomTrap> roomTrapList = new ArrayList<RoomTrap>();

    private String[] trapNamePrefix = { "Dark", "Bloody", "Dreadic", "Sharp", "Rusty","Poisoned"};
    private String[] trapName = {"Spring","Dead","Bear","Switch","Rat"};
    private String[] trapNameSuffix = {"Trap","Pit","Deadfall","Loop","Spike"};

	//CONSTRUCTOR OVERLOAD
    TrapsList(int _trapQuantity){
        for(int i = 0 ; i < _trapQuantity; i++){

            RoomTrap generatedRoomTrap = new RoomTrap(	GenerateTrapName(),
														GenerateNumberBetween(1,20),
														GenerateNumberBetween(1,20),
														GenerateNumberBetween(1,20));

            roomTrapList.add(generatedRoomTrap);
        }

    }


	//AUX FUNCTS
    private String GenerateTrapName(){
		
        String generatedTrapName="";
        int trapNamePrefixNumber = GenerateNumberBetween(0,trapNamePrefix.length);
        int trapNameNumber = GenerateNumberBetween(0,trapName.length);
        int trapNameSuffixNumber = GenerateNumberBetween(0,trapNameSuffix.length);

        //for version 26 only // generatedTrapName = String.join(" ",trapNamePrefix[trapNamePrefixNumber],trapName[trapNameNumber],trapNameSuffix[trapNameSuffixNumber]);
        generatedTrapName = trapNamePrefix[trapNamePrefixNumber]+" "+trapName[trapNameNumber]+" "+trapNameSuffix[trapNameSuffixNumber];


        return generatedTrapName;
    }

    private int GenerateNumberBetween(int _min, int _max){
        Random r = new Random();
        int result = r.nextInt(_max-_min) + _min;
        return result;
    }

}
