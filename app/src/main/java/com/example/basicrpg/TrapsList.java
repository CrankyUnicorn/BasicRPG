package com.example.basicrpg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TrapsList {

    private static List<RoomTrap> roomTrapList = new ArrayList<RoomTrap>();

    private static final TrapsList ourInstance = new TrapsList(GameSettings.TRAP_TYPES);//

    public static TrapsList getInstance() {
        return ourInstance;
    }





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

            RoomTrap generatedRoomTrap = new RoomTrap("No Trap",0,0,0);

            roomTrapList.add(generatedRoomTrap);
    }


    //ACESSOR
    public static RoomTrap GetTrapFromList(int index){
        return roomTrapList.get(index);
    }

    public static  RoomTrap GetRandomTrap(){
        Log.d("TrapList","Create Trap!");
        return roomTrapList.get(Util.GenerateNumberBetween(0,roomTrapList.size()-1));
    }

    public int GetTrapListLength(){
        return roomTrapList.size();
    }


	//AUX FUNCTS




}
