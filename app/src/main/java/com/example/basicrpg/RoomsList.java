package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;

public class RoomsList {


    private List<DungeonRoom> roomsList = new ArrayList<DungeonRoom>();

    private int numberOfRooms;

    //CONSTRUCTOR OVERLOAD
    RoomsList(int _numberOfRooms){

        numberOfRooms = _numberOfRooms;


        GenerateRooms(_numberOfRooms);
    }

    //ACESSOR
    public DungeonRoom GetDungeonRoomById(int _id){
        DungeonRoom selectedRoom = new DungeonRoom();

        for(DungeonRoom room : roomsList){
            if(room.RoomID() == _id){

                selectedRoom = new DungeonRoom( room.ThisRoomTrap(),
                                                room.RoomID(),
                                                room.RoomName(),
                                                room.RoomDescription(),
                                                room.RoomExitsId(),
                                                room.RoomExitsNames());

                break;
            }
        }

        return  selectedRoom;
    }

    //AUX
    public DungeonRoom GetDungeonRoomByIndex(int _index){
        DungeonRoom selectedRoom = null;

        if(_index <= roomsList.size()){

            DungeonRoom room = roomsList.get(_index);

            selectedRoom = CloneDungeonRoom(room);
        }

        if(selectedRoom == null){
            selectedRoom =  roomsList.get(0);
        }

        return selectedRoom;
    }

    //AUX
    private void GenerateRooms(int _numberOfRooms){

        for (int i = 0; i <_numberOfRooms; i++){

            DungeonRoom DungeonRoomOut = new DungeonRoom(
                    room.ThisRoomTrap(),
                    room.RoomID(),
                    room.RoomName(),
                    room.RoomDescription(),
                    room.RoomExitsId(),
                    room.RoomExitsNames()
            );

            roomsList.add(DungeonRoomOut);
        }
    }

    //AUX
    private DungeonRoom CloneDungeonRoom (DungeonRoom room){

        DungeonRoom DungeonRoomOut = new DungeonRoom(
                room.ThisRoomTrap(),
                room.RoomID(),
                room.RoomName(),
                room.RoomDescription(),
                room.RoomExitsId(),
                room.RoomExitsNames()
        );

        return DungeonRoomOut;
    }
}
