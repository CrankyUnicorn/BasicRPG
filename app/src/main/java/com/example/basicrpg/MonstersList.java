package com.example.basicrpg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MonstersList {

    private static List<RoomMonster> monstersList = new ArrayList<RoomMonster>();

    private static final MonstersList ourInstance = new MonstersList(GameSettings.MONSTER_TYPES);//

    public static MonstersList getInstance() {
        return ourInstance;
    }





	//CONSTRUCTOR OVERLOAD
    private MonstersList(int _monstersQuantity){

        NoMonster(); //No item

        for(int i = 0 ; i < _monstersQuantity; i++){

            RoomMonster generatedMonster = new RoomMonster(	DungeonNameGenerator.GenerateMonsterName(),
                                                            DungeonNameGenerator.GenerateMonsterDescription());

            monstersList.add(generatedMonster);
        }

    }

    private void NoMonster(){

        RoomMonster generatedMonster = new RoomMonster("No Monster","No Description");

        monstersList.add(generatedMonster);
    }


    //ACESSOR
    public static RoomMonster GetMonsterFromList(int index){
        return monstersList.get(index);
    }

    public static  RoomMonster GetRandomMonster(){
        Log.d("MonstersList","Create Monster!");
        return monstersList.get(Util.GenerateNumberBetween(0,monstersList.size()-1));
    }

    public int GetMonstersListLength(){
        return monstersList.size();
    }


	//AUX FUNCTS

}
