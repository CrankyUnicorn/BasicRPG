package com.example.basicrpg;

import java.util.HashMap;
import java.util.Map;

public class DungeonRoom{

    private IRoomTrap thisRoomTrap;

    private int roomId;
    private String roomName;
    private String roomDescription;

    private boolean roomExplored;

    private int[] roomExitsId;
    private String[] roomExitsNames;


	//CONSTRUCTOR OVERLOAD
    DungeonRoom(IRoomTrap _thisRoomTrap, int _roomId, String _roomName,String _roomDescription, int[] _roomExitsId, String[] _roomExitsNames){

		thisRoomTrap =_thisRoomTrap;

		roomId = _roomId;
		roomName = _roomName;
		roomDescription = _roomDescription;

		roomExitsId = _roomExitsId;
		roomExitsNames = _roomExitsNames;

    }

	//CONSTRUCTOR
	DungeonRoom(){}

	
	//ACESSORS
	public IRoomTrap ThisRoomTrap(){
		return thisRoomTrap;
	}

	public int RoomID(){
		return roomId;
	}
	
	public String RoomName(){
		return roomName;
	}
	 
	public String RoomDescription(){
		return roomDescription;
	}

	public boolean RoomExplored(){ return roomExplored;	}

	public int[] RoomExitsId(){
		return roomExitsId;
	}
	
	public String[] RoomExitsNames(){
		return roomExitsNames;
	}


	//TRAPS COMMANDS
	public void ExecuteTrap(){

    	//thisRoomTrap.TriggerTrap();

		//thisRoomTrap.DetectTrap();

		//thisRoomTrap.DisarmTrap();
	}




}
