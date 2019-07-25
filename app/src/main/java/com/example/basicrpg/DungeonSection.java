package com.example.basicrpg;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.example.basicrpg.Util;

public class DungeonSection {

    private int dungeonSectionId;

    public int GetDungeonSectionId() {
        return dungeonSectionId;
    }


    private String dungeonSectionName;

    public String GetDungeonSectionName() {return dungeonSectionName;}


    private int numberOfRooms;

    public int GetNumberOfRooms() {return numberOfRooms;}


    private List<DungeonRoom> childRooms = new ArrayList<DungeonRoom>();

    public List<DungeonRoom> GetChildRooms() {return childRooms;}



    //CONSTRUCTOR OVERLOAD
    DungeonSection( int _numberOfRooms){

        numberOfRooms = _numberOfRooms;

        dungeonSectionName = DungeonNameGenerator.GenerateLevelName();

        dungeonSectionId = Util.GetId();

        SetDungeonSection();
    }

    DungeonSection(){}


    //AUX
    private void SetDungeonSection(){

        for (int i = 0; i <numberOfRooms; i++){

            Log.d("RoomList","Create Room!");


            DungeonRoom dungeonRoom = RoomsList.getInstance().GetRandomRoom(); //get a random room from a room List

            childRooms.add(dungeonRoom);

        }
    }
}

