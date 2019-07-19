package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;
import com.example.basicrpg.Util;

public class DungeonLevel {

    private String levelName;

    private int dungeonLevelId;

    private int numberOfSections;

    private int numberOfRooms;



    public List<DungeonSection> thisDungeonSection = new ArrayList<DungeonSection>();

    //CONSTRUCTOR OVERLOAD
    DungeonLevel( int _numberOfSections, int _numberOfRooms){

        levelName = DungeonNameGenerator.GenerateLevelName();
        dungeonLevelId = Util.GetId();
        numberOfSections = _numberOfSections;
        numberOfRooms = _numberOfRooms;



        setDungeonLevel();
    }

    DungeonLevel(){}

    private void setDungeonLevel(){

        int roomRemainder = numberOfRooms;


        for (int i = 0; i <numberOfSections; i++) {

            int levelRooms = Util.GenerateNumberBetween(1,roomRemainder-(numberOfRooms-i));

            DungeonSection iDungeonSector = new DungeonSection(levelRooms);

            thisDungeonSection.add(iDungeonSector);
        }

    }

}
