package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;
import com.example.basicrpg.Util;

public class DungeonSection {

    private int dungeonSectionId;

    private int numberOfRooms;

    public List<DungeonRoom> thisDungeonSection = new ArrayList<DungeonRoom>();


    //CONSTRUCTOR OVERLOAD
    DungeonSection( int _numberOfRooms){

        numberOfRooms = _numberOfRooms;
        dungeonSectionId = Util.GetId();

        SetDungeonSection(_numberOfRooms);
    }

    //AUX
    private void SetDungeonSection(int _numberOfRooms){

        for (int i = 0; i <_numberOfRooms; i++){

            DungeonRoom dungeonRoom = roomList; //get a room from a room List


            thisDungeonSection.add(dungeonRoom);
        }
    }
}

