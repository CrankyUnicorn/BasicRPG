package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrapsList {

    private static final TrapsList ourInstance = new TrapsList(GameSettings.trapTypes);//

    public static TrapsList getInstance() {
        return ourInstance;
    }

    private static List<RoomTrap> roomTrapList = new ArrayList<RoomTrap>();



	//CONSTRUCTOR OVERLOAD
    private TrapsList(int _trapQuantity){

        NoTrap(); //No trap

        for(int i = 0 ; i < _trapQuantity; i++){

            RoomTrap generatedRoomTrap = new RoomTrap(	DungeonNameGenerator.GenerateTrapName(),
														Util.GenerateNumberBetween(1,20),
                                                        Util.GenerateNumberBetween(1,20),
                                                        Util.GenerateNumberBetween(1,20));

            roomTrapList.add(generatedRoomTrap);
        }

    }

    private void NoTrap(){

            RoomTrap generatedRoomTrap = new RoomTrap(	"No Trap",0,0,0);

            roomTrapList.add(generatedRoomTrap);
    }


    //ACESSOR
    public static RoomTrap GetTrapFromList(int index){
        return roomTrapList.get(index);
    }

    public static  RoomTrap GetRandomTrap(){
        return roomTrapList.get(Util.GenerateNumberBetween(0,roomTrapList.size()));
    }

    public int GetTrapListLength(){
        return roomTrapList.size();
    }


	//AUX FUNCTS




}
