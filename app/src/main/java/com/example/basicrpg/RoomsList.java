package com.example.basicrpg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RoomsList {

    private static final RoomsList ourInstance = new RoomsList(GameSettings.ROOM_TYPES);//

    public static RoomsList getInstance() {
        return ourInstance;
    }


    private List<DungeonRoom> roomsList = new ArrayList<DungeonRoom>();


    private int numberOfRooms;




    //CONSTRUCTOR OVERLOAD
    private RoomsList(int _numberOfRooms){

        GenerateRooms(_numberOfRooms);

        numberOfRooms = roomsList.size();
    }

	//-----------------



	private DungeonRoom EntranceRoom(){

        int[] entranceDoors = {2,0,0,0};
        String[] entranceDoorsDescription = {"Enter Dungeon","","",""};

            DungeonRoom DungeonRoomOut = new DungeonRoom(
                    TrapsList.GetTrapFromList(0),
					MonstersList.GetMonsterFromList(0),
					TreasuresList.GetTreasureFromList(0),
                    0,
                    "Entrance",
                    "You find yourself at the entrance of a Dungeon",
                    true,
                    ImageReferences.imageOutside[Util.GenerateNumberBetween(0,ImageReferences.imageOutside.length)],
                    entranceDoors,
                    entranceDoorsDescription
            );

            return DungeonRoomOut;

    }

    private DungeonRoom ExitRoom(){

        int[] entranceDoors = {0,0,0,0};
        String[] entranceDoorsDescription = {"Exit Dungeon","","",""};

        DungeonRoom DungeonRoomOut = new DungeonRoom(
                TrapsList.GetTrapFromList(0),
                MonstersList.GetMonsterFromList(0),
                TreasuresList.GetTreasureFromList(0),
                0,
                "Exit",
                "You found yourself out of the dungeon",
                true,
                ImageReferences.imageOutside[Util.GenerateNumberBetween(0,ImageReferences.imageOutside.length)],
                entranceDoors,
                entranceDoorsDescription
        );

        return DungeonRoomOut;

    }


    //AUX BUILDER
    private void GenerateRooms(int _numberOfRooms){


        for (int i = 0; i <_numberOfRooms; i++){

            int[] entranceDoors={0,0,0,0};
            String[] entranceDoorsDescription={"","","",""};

            DungeonRoom DungeonRoomOut = new DungeonRoom(
                    TrapsList.GetRandomTrap(),
					MonstersList.GetRandomMonster(),
					TreasuresList.GetRandomTreasure(),
                    0,
                    DungeonNameGenerator.GenerateRoomName(),
                    DungeonNameGenerator.GenerateRoomDescription(),
                    false,
                    ImageReferences.imageCastle[Util.GenerateNumberBetween(0,ImageReferences.imageCastle.length)],
                    entranceDoors,
                    entranceDoorsDescription
            );

            roomsList.add(DungeonRoomOut);
        }

        Log.d("Generated Rooms ","Done!");
        //Log.d("RoomList Q:", String.valueOf(roomsList.size()));
        //Log.d("Room Zero Ids: ", Arrays.toString(roomsList.get(0).RoomExitsId()));
        //Log.d("Room Zero Names : ", Arrays.toString(roomsList.get(0).RoomExitsNames()));
    }

  
	//---------------------

    //AUX
    public DungeonRoom CloneDungeonRoomById(int _index){

        return  CloneDungeonRoom(GetDungeonRoomByIndex(_index));
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


        DungeonRoom selectedRoom = roomsList.get(Util.GenerateNumberBetween(1, roomsList.size()));

        return CloneDungeonRoom(selectedRoom);
    }

    //AUX
    public DungeonRoom GetEntranceRoom(){

        return CloneDungeonRoom(EntranceRoom());
    }
    //AUX
    public DungeonRoom GetExitRoom(){

        return CloneDungeonRoom(ExitRoom());
    }


    //AUX CLONER
    private DungeonRoom CloneDungeonRoom (DungeonRoom room){

        DungeonRoom DungeonRoomOut = new DungeonRoom(
                room.ThisRoomTrap(),
                room.ThisRoomMonster(),
                room.ThisRoomTreasure(),
                0,
                room.RoomName(),
                room.RoomDescription(),
                room.RoomExplored(),
                room.RoomImage(),
                room.GetRoomExitsId(),
                room.GetRoomExitsNames()
        );

        return DungeonRoomOut;
    }
}
