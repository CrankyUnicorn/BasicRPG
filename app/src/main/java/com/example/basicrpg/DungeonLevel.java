package com.example.basicrpg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.example.basicrpg.Util;

public class DungeonLevel {

    private String dungeonLevelName;

    public String GetDungeonLevelName() {
        return dungeonLevelName;
    }


    private int dungeonLevelId;

    public int GetDungeonLevelId() {
        return dungeonLevelId;
    }


    private int numberOfSections;

    public int GetNumberOfSections() {
        return numberOfSections;
    }


    private int numberOfRooms;

    public int GetNumberOfRooms() {
        return numberOfRooms;
    }


    private List<DungeonSection> childSections = new ArrayList<DungeonSection>();

    public List<DungeonSection> GetChildSections(){return childSections;}


        //CONSTRUCTOR OVERLOAD
    DungeonLevel( int _numberOfSections, int _numberOfRooms){

        dungeonLevelName = DungeonNameGenerator.GenerateLevelName();
        dungeonLevelId = Util.GetId();
        numberOfSections = _numberOfSections;
        numberOfRooms = _numberOfRooms;

        setDungeonLevel();
    }

    DungeonLevel(){}

    private void setDungeonLevel(){

        int roomRemainder = numberOfRooms;

        for (int i = numberOfSections-1; i >=1; i--) {

            Log.d("SectionsList","Create Section!");

            int sectionRooms;

            if(i>0) {

                 sectionRooms = Util.GenerateNumberBetween(1, roomRemainder - i);

            }else{

                sectionRooms = roomRemainder;
            }

            roomRemainder -= sectionRooms;

            childSections.add(new DungeonSection(sectionRooms));

            //Log.d("SectionRooms", String.valueOf(sectionRooms));
        }

    }

}
