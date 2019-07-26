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





	//CONSTRUCTOR OVERLOAD
    private TreasuresList(int _treasuresQuantity){

        NoTreasure(); //No items

        for(int i = 0 ; i < _treasuresQuantity; i++){

            RoomTreasure generatedTreasure = new RoomTreasure(	DungeonNameGenerator.GenerateTreasureName(),
											"treasure description");

            treasuresList.add(generatedTreasure);
        }

    }

    private void NoTreasure(){

        RoomTreasure generatedTreasure = new RoomTreasure("No Treasure","No Description");

            treasuresList.add(generatedTreasure);
    }


    //ACESSOR
    public static RoomTreasure GetTreasureFromList(int index){
        return treasuresList.get(index);
    }

    public static  RoomTreasure GetRandomTreasure(){
        Log.d("TreasureList","Create Treasure!");
        return treasuresList.get(Util.GenerateNumberBetween(0,treasuresList.size()-1));
    }

    public int GetTreasuresListLength(){
        return treasuresList.size();
    }


	//AUX FUNCTS

}
