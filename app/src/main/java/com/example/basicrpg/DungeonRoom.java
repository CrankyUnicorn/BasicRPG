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



	//---EXITS---
	public void SetRoomExitsId(int _id , int _index){
		if(_index<roomExitsId.length){
		roomExitsId[_index] = _id;}
	}

	public int[] GetRoomExitsId(){
		return roomExitsId;
	}


	public int GetRoomExitsIdByIndex(int _index){
		if(_index<roomExitsId.length){
			return roomExitsId[_index];
		}
		return roomExitsId[0];
	}

	//---NAMES---
	public void SetRoomExitsNames(String _string , int _index){
		if(_index<roomExitsNames.length){
		roomExitsNames[_index] = _string;}
	}
	
	public String[] GetRoomExitsNames(){
		return roomExitsNames;
	}

	public String GetRoomExitsNamesByIndex(int _index){
		if(_index<roomExitsNames.length){
		return roomExitsNames[_index];}
		return roomExitsNames[0];
	}


	//TRAPS COMMANDS
	public void ExecuteTrap(){

    	//thisRoomTrap.TriggerTrap();

		//thisRoomTrap.DetectTrap();

		//thisRoomTrap.DisarmTrap();
	}




}
