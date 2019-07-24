package com.example.basicrpg;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Dungeon {


    private String dungeonName;

    private String dungeonCreation;

    private int dungeonTotalLevels;
    private int dungeonTotalSections;
    private int dungeonTotalRooms;

    private int dungeonTotalLevelsExplored;
    private int dungeonTotalSectionsExplored;
    private int dungeonTotalRoomsExplored;

    private List<DungeonLevel> childLevels = new ArrayList<DungeonLevel>();

    //private List<DungeonLevel> listDungeonLevels = new ArrayList<DungeonLevel>();
    //private List<DungeonSection> listDungeonSections = new ArrayList<DungeonSection>();
    private List<DungeonRoom> listDungeonRooms = new ArrayList<DungeonRoom>();


    //CONSTRUCTOR
   Dungeon(){

       VerifyDungeonList();

       CreateDungeonLevels();
    }


    //VERIFIER
    private void VerifyDungeonList(){

        dungeonName = GameSettings.dungeonName.isEmpty() ? DungeonNameGenerator.GenerateDungeonName() : GameSettings.dungeonName;

        dungeonTotalLevels = GameSettings.dungeonLevels < 1 ? 1 : GameSettings.dungeonLevels;

        dungeonTotalSections = GameSettings.dungeonSections < dungeonTotalLevels ? dungeonTotalLevels: GameSettings.dungeonSections;

        dungeonTotalRooms = GameSettings.dungeonRooms < dungeonTotalSections ? dungeonTotalSections : GameSettings.dungeonRooms;

    }


    //CREATE DUNGEON STRUCTURE
    private void CreateDungeonLevels(){

        int sectionsRemainder = dungeonTotalSections;
        int roomRemainder = dungeonTotalRooms;


        for (int i = dungeonTotalLevels-1; i >=0; i--) {

            Log.d("Levels","Create Level!");

            int levelSections;
            int levelRooms;


            if(i>0) {

                levelSections = Util.GenerateNumberBetween(1, sectionsRemainder - i);
                levelRooms = Util.GenerateNumberBetween(levelSections, roomRemainder - (sectionsRemainder - levelSections));

            }else{

                levelSections = sectionsRemainder;
                levelRooms = roomRemainder;
            }

            sectionsRemainder -= levelSections;
            roomRemainder -= levelRooms;

            //Log.d("levelSectors", String.valueOf(levelSectors));
            //Log.d("levelRooms", String.valueOf(levelRooms));
            //Log.d("sectorRemainder", String.valueOf(sectorRemainder));
            //Log.d("roomRemainder", String.valueOf(roomRemainder));


            childLevels.add(new DungeonLevel(levelSections,levelRooms));
        }

    }

    //ACESSORS
    //CHILD LIST ACESSOR
    public List<DungeonLevel> GetChildLevels(){return childLevels;}

    //ROOM LIST ACESSOR
    public void SetDungeonRooms(DungeonRoom _room){listDungeonRooms.add(_room);}
    public List<DungeonRoom> GetDungeonRooms(){return listDungeonRooms;}


    //NAME ACESSOR
    public void SetDungeonName(String _dungeonName){

        if(dungeonName.isEmpty()){

            dungeonName = _dungeonName;
        }

    }

    public String GetDungeonName(){return dungeonName;}

    //Creation ACESSOR
    public void SetDungeonCreation(){

        if(dungeonCreation.isEmpty()){
            Date nowDate = new Date();
            DateFormat thisDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            dungeonCreation = thisDateFormat.format(nowDate);
        }

    }

    public String GetDungeonCreation(){return dungeonCreation;}

    //EXPLORED
    public int GetDungeonTotalLevelsExplored(){return dungeonTotalLevelsExplored;}

    public int GetDungeonTotalSectionsExplored(){return dungeonTotalSectionsExplored;}

    public int GetDungeonTotalRoomsExplored(){return dungeonTotalRoomsExplored;}



}