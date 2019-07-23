package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;
import com.example.basicrpg.Util;

public class DungeonSection {

    private int dungeonSectionId;

    public int GetDungeonSectionId() {
        return dungeonSectionId;
    }

    private String dungeonSectionName;

    public String GetDungeonSectionName() {
        return dungeonSectionName;
    }

    private int numberOfRooms;

    public int GetNumberOfRooms() {
        return numberOfRooms;
    }


    public List<DungeonRoom> thisDungeonSection = new ArrayList<DungeonRoom>();

    public List<DungeonRoom> GetChildList() {
        return thisDungeonSection;
    }

    //CONSTRUCTOR OVERLOAD
    DungeonSection( int _numberOfRooms){

        numberOfRooms = _numberOfRooms;

        dungeonSectionName = DungeonNameGenerator.GenerateLevelName();

        dungeonSectionId = Util.GetId();

        SetDungeonSection(_numberOfRooms);
    }

    //AUX
    private void SetDungeonSection(int _numberOfRooms){

        for (int i = 0; i <_numberOfRooms; i++){

            DungeonRoom dungeonRoom = RoomsList.getInstance().GetRandomRoom(); //get a random room from a room List


            thisDungeonSection.add(dungeonRoom);
        }
    }
}

