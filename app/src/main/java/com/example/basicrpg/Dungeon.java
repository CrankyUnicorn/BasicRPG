package com.example.basicrpg;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.basicrpg.DungeonNameGenerator;
import com.example.basicrpg.Util;




public class Dungeon {


    private String dungeonName;

    private int dungeonId;

    private int dungeonTotalLevels;
    private int dungeonTotalSections;
    private int dungeonTotalRooms;

    private int dungeonTotalLevelsExplored;
    private int dungeonTotalSectionsExplored;
    private int dungeonTotalRoomsExplored;

    private List<DungeonLevel> listDungeonLevels = new ArrayList<DungeonLevel>();


    //CONSTRUCTOR
   Dungeon(String _dungeonName, int _dungeonLevels, int _dungeonSections, int _dungeonRooms){

       dungeonName = _dungeonName == "" ? DungeonNameGenerator.GenerateDungeonName() : _dungeonName;

       dungeonTotalLevels = _dungeonLevels < 1 ? 1 : _dungeonLevels ;

       dungeonTotalSections = _dungeonSections < _dungeonLevels ? _dungeonLevels : _dungeonSections;

       dungeonTotalRooms = _dungeonRooms < dungeonTotalSections ? dungeonTotalSections : _dungeonRooms;

       PopulatorDungeonList();
    }

    //CONSTRUCTOR OVERLOAD
    Dungeon(){}


    //LIST POPULATOR
    private void PopulatorDungeonList(){

        int sectionsRemainder = dungeonTotalSections;

        int roomRemainder = dungeonTotalRooms;


        for (int i = dungeonTotalLevels-1; i >=0; i--) {

            int levelSections;
            int levelRooms;
            Log.d("Levels","Create Level!");
            if(i>0) {

                levelSections = Util.GenerateNumberBetween(1, sectionsRemainder - i);
                levelRooms = Util.GenerateNumberBetween(levelSections, roomRemainder - (sectionsRemainder - levelSections));

            }else{
                levelSections = sectionsRemainder;
                levelRooms = roomRemainder;
            }
            Log.d("levelSectors", String.valueOf(levelSections));
            Log.d("levelRooms", String.valueOf(levelRooms));

            sectionsRemainder -= levelSections;
            roomRemainder -= levelRooms;

            //Log.d("levelSectors=", String.valueOf(levelSectors));
            //Log.d("levelRooms=", String.valueOf(levelRooms));
            //Log.d("sectorRemainder=", String.valueOf(sectorRemainder));
            //Log.d("roomRemainder=", String.valueOf(roomRemainder));

            DungeonLevel iDungeonLevel = new DungeonLevel(levelSections, levelRooms);

            listDungeonLevels.add(iDungeonLevel);
        }

    }

    //ACESSORS
//
    public List<DungeonLevel> GetChildList(){return listDungeonLevels;}

    //NAME ACESSOR
    public void SetDungeonName(String _dungeonName){

        if(dungeonName==null){

            dungeonName = _dungeonName;
        }

    }

    public String GetDungeonName(){

        return dungeonName;
    }

    //ID ACESSOR
    public void SetDungeonId(int _dungeonId){

        if(dungeonId==0){

            dungeonId = _dungeonId;
        }

    }

    public int GetDungeonId(){

        return dungeonId;
    }


    //DungeonTotalLevels ACESSOR
    public void SetDungeonTotalLevels(int _dungeonTotalLevels){

        if(dungeonTotalLevels==0){

            dungeonTotalLevels = _dungeonTotalLevels;
        }else{}

    }

    public int GetDungeonTotalLevels(){

        //maybe usefull to recalculate all the levels on demand
        return dungeonTotalLevels;
    }


    //DungeonTotalSections ACESSOR
    public void SetDungeonTotalSections(int _dungeonTotalSections){

        if(dungeonTotalSections == 0){

            dungeonTotalSections = _dungeonTotalSections;
        }
    }

    public int GetDungeonTotalSections(){

        //maybe usefull to recalculate all the sections on demand
        return dungeonTotalSections;
    }


    //DungeonTotalRooms ACESSOR
    public void SetDungeonTotalRooms(int _dungeonTotalRooms){

        if(dungeonTotalRooms == 0 ){

            dungeonTotalRooms = _dungeonTotalRooms;
        }

    }

    public int GetDungeonTotalRooms(){

        //maybe usefull to recalculate all the rooms on demand
        return dungeonTotalRooms;
    }


    //DungeonTotalLevelsExplored ACESSOR
    public void SetDungeonTotalLevelsExplored(int _dungeonTotalLevelsExplored){

        dungeonTotalLevelsExplored = _dungeonTotalLevelsExplored;
    }

    public int GetDungeonTotalLevelsExplored(){

        return dungeonTotalLevelsExplored;
    }


    //DungeonTotalSectionsExplored ACESSOR
    public void SetDungeonTotalSectionsExplored(int _dungeonTotalSectionsExplored){

        dungeonTotalSectionsExplored = _dungeonTotalSectionsExplored;
    }

    public int GetDungeonTotalSectionsExplored(){

        return dungeonTotalSectionsExplored;
    }


    //DungeonTotalRoomsExplored ACESSOR
    public void SetDungeonTotalRoomsExplored(int _dungeonTotalRoomsExplored){

        dungeonTotalRoomsExplored = _dungeonTotalRoomsExplored;
    }

    public int GetDungeonTotalRoomsExplored(){

        return dungeonTotalRoomsExplored;
    }



}