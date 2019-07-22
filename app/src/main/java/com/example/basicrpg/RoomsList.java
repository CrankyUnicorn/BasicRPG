package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;

public class RoomsList {
    private static final RoomsList ourInstance = new RoomsList(GameSettings.roomTypes);//

    public static RoomsList getInstance() {
        return ourInstance;
    }

    private List<DungeonRoom> roomsList = new ArrayList<DungeonRoom>();

    private int numberOfRooms;

    //CONSTRUCTOR OVERLOAD
    private RoomsList(int _numberOfRooms){

        numberOfRooms = _numberOfRooms;


        GenerateRooms(_numberOfRooms);
    }

    //ACESSOR
    public DungeonRoom CloneDungeonRoomById(int _id){
        DungeonRoom selectedRoom = new DungeonRoom();

        for(DungeonRoom room : roomsList){
            if(room.RoomID() == _id){

                selectedRoom = new DungeonRoom( room.ThisRoomTrap(),
                                                Util.GetId(),
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

        if(_index < roomsList.size()){

            DungeonRoom room = roomsList.get(_index);

            selectedRoom = CloneDungeonRoom(room);
        }

        if(selectedRoom == null){
            selectedRoom =  roomsList.get(0);
        }

        return selectedRoom;
    }

    //AUX
    public DungeonRoom GetRandomRoom(){
        DungeonRoom selectedRoom = null;

        selectedRoom = roomsList.get(Util.GenerateNumberBetween(1, roomsList.size()));

        return selectedRoom;
    }

    //AUX BUILDER
    private void GenerateRooms(int _numberOfRooms){

        GenerateInitialRooms();

        for (int i = 0; i <_numberOfRooms; i++){

            int[] entranceDoors=null;
            String[] entranceDoorsDescription=null;



            DungeonRoom DungeonRoomOut = new DungeonRoom(
                    TrapsList.GetRandomTrap(),
                    Util.GetId(),
                    DungeonNameGenerator.GenerateRoomName(),
                    DungeonNameGenerator.GenerateRoomDescription(),
                    entranceDoors,
                    entranceDoorsDescription
            );

            roomsList.add(DungeonRoomOut);
        }
    }

    private void GenerateInitialRooms(){

        int[] entranceDoors = {1,2,3};
        String[] entranceDoorsDescription = {"Path to the left","Down the stairs","Go back"};

            DungeonRoom DungeonRoomOut = new DungeonRoom(
                    TrapsList.GetTrapFromList(0),
                    Util.GetId(),
                    "Entrance",
                    "You find yourself at the entrance of a Dungeon",
                    entranceDoors,
                    entranceDoorsDescription
            );

            roomsList.add(DungeonRoomOut);

    }

    //AUX CLONER
    private DungeonRoom CloneDungeonRoom (DungeonRoom room){

        DungeonRoom DungeonRoomOut = new DungeonRoom(
                room.ThisRoomTrap(),
                Util.GetId(),
                room.RoomName(),
                room.RoomDescription(),
                room.RoomExitsId(),
                room.RoomExitsNames()
        );

        return DungeonRoomOut;
    }
}
