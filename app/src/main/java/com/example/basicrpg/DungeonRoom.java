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

    private int imageRoom;

    private boolean roomExplored;

    private int[] roomExitsId;
    private String[] roomExitsNames;


	//CONSTRUCTOR OVERLOAD
    DungeonRoom(IRoomTrap _thisRoomTrap,IRoomMonster _thisRoomMonster ,IRoomTreasure _thisRoomTreasure, int _roomId, String _roomName,String _roomDescription,boolean _roomExplored,int _imageRoom, int[] _roomExitsId, String[] _roomExitsNames){

		this.thisRoomTrap =_thisRoomTrap;
		this.thisRoomMonster = _thisRoomMonster;
		this.thisRoomTreasure = _thisRoomTreasure;

		roomId = _roomId;
		roomName = _roomName;
		roomDescription = _roomDescription;

		imageRoom = _imageRoom;
		roomExplored = _roomExplored;

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
    	String output="";
		output += roomDescription;
		output += thisRoomMonster.MonsterPresent() ? thisRoomMonster.MonsterDead() ?"\n Theres a dead foe on the ground" :"\n Theres a foe here": "\n There is no one here";
		output += thisRoomTrap.TrapDetected() ? "\n You found a trap on this room" : "\n You found no trap on this room";
		output += thisRoomTreasure.TreasureVisible() ? "\n You found a treasure on this room" : "\n You found no treasure on this room";
		output += "\n linha de testes";
    	return output ;
	}



	public int RoomImage(){ return imageRoom;	}

	public boolean RoomExplored(){ return roomExplored;	}

	//---SET ID---
	public void ResetRoomId(){

		roomId = Util.GetRoomId();
	}

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
