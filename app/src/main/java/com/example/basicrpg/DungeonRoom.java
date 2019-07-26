package com.example.basicrpg;

import java.util.HashMap;
import java.util.Map;

public class DungeonRoom{

    private IRoomTrap thisRoomTrap;
    private IRoomMonster thisRoomMonster;
    private IRoomTreasure thisRoomTreasure;

    private int roomId;
    private String roomName;
    private String roomDescription;

    private boolean roomExplored;

    private int[] roomExitsId;
    private String[] roomExitsNames;


	//CONSTRUCTOR OVERLOAD
    DungeonRoom(IRoomTrap _thisRoomTrap,IRoomMonster _thisRoomMonster ,IRoomTreasure _thisRoomTreasure, int _roomId, String _roomName,String _roomDescription, int[] _roomExitsId, String[] _roomExitsNames){

		this.thisRoomTrap =_thisRoomTrap;
		this.thisRoomMonster = _thisRoomMonster;
		this.thisRoomTreasure = _thisRoomTreasure;

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
	public IRoomMonster ThisRoomMonster(){return thisRoomMonster;}
	public IRoomTreasure ThisRoomTreasure(){return thisRoomTreasure;}

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



	//---EXITS---
	public void SetRoomExitsId(int _id , int _index){

		roomExitsId[_index] = _id;
	}

	public int[] GetRoomExitsId(){
		return roomExitsId;
	}


	public int GetRoomExitIdByIndex(int _index){

		return roomExitsId[_index];
	}

	//---NAMES---
	public void SetRoomExitsNames(String _string , int _index){

		roomExitsNames[_index] = _string;
	}
	
	public String[] GetRoomExitsNames(){
		return roomExitsNames;
	}

	public String GetRoomExitNameByIndex(int _index){

		return roomExitsNames[_index];
	}


	//TRAPS COMMANDS
	public void ExecuteTrap(){

    	//thisRoomTrap.TriggerTrap();

		//thisRoomTrap.DetectTrap();

		//thisRoomTrap.DisarmTrap();
	}




}
