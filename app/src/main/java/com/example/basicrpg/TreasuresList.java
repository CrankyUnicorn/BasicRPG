package com.example.basicrpg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TreasuresList {

    private static List<RoomTreasure> treasuresList = new ArrayList<RoomTreasure>();

    private static final TreasuresList ourInstance = new TreasuresList(GameSettings.TREASURE_TYPES);//

    public static TreasuresList getInstance() {
        return ourInstance;
    }


private String treasureName;
private String treasureDescription;


	//CONSTRUCTOR OVERLOAD
    private TreasuresList(int _treasuresQuantity){

        NoTreasure(); //No treasure


        for(int i = 0 ; i < _treasuresQuantity; i++){

            RoomTreasure generatedTreasure = new RoomTreasure(
                    true,
                    DungeonNameGenerator.GenerateTreasureName(),
                    DungeonNameGenerator.GenerateTreasureDescription(),
                    Util.GenerateNumberBetween(0,4),
                    Util.GenerateNumberBetween(0,4)<2?false:true,
                    true,null);

            treasuresList.add(generatedTreasure);
        }

    }

    private void NoTreasure(){

        RoomTreasure generatedTreasure = new RoomTreasure(
                false,
                "No Treasure",
                "No Description",
                0,
                false,
                false,null);

            treasuresList.add(generatedTreasure);
    }
	
	public static RoomTreasure GoldenBox(){

        RoomTreasure generatedTreasure = new RoomTreasure(true,
                "Golden Box",
                "Strange Shiny Golden Box",
                0,
                true,
                false, ItemsList.MementoMori());

            return generatedTreasure;
    }

    //ACESSOR
    public static RoomTreasure GetTreasureFromList(int index){
		if(index>treasuresList.size()){
		
			return CloneTreasure(treasuresList.get(index));
		
		}else{
			
			return CloneTreasure(treasuresList.get(0));
		
		}  
  }

    public static  RoomTreasure GetRandomTreasure(){
       // Log.d("TreasureList","Create Treasure!");
        return CloneTreasure(treasuresList.get(Util.GenerateNumberBetween(0,treasuresList.size()-1)));
    }

    public int GetTreasuresListLength(){
        return treasuresList.size();
    }


	//AUX FUNCTS
	//AUX CLONER
    private static RoomTreasure CloneTreasure (RoomTreasure _treasure){

        RoomTreasure newRoomTreasure = new RoomTreasure(
                _treasure.TreasurePresent(),
                _treasure.TreasureName(),
                _treasure.TreasureDescription(),
                _treasure.TreasureToDetect(),
                _treasure.TreasureVisible(),
                false,//must be always false if you intend is to clone
                ItemsList.CloneItem(_treasure.GetItem())


		);
        
        return newRoomTreasure;
    }

}
