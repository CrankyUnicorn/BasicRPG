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

    private int imageRoomLight;
    private int imageRoomDark;

    private boolean roomExplored;

    private int[] roomExitsId;
    private String[] roomExitsNames;


	//CONSTRUCTOR OVERLOAD
    DungeonRoom(IRoomTrap _thisRoomTrap,IRoomMonster _thisRoomMonster ,
	IRoomTreasure _thisRoomTreasure, int _roomId, String _roomName,
	String _roomDescription,boolean _roomExplored,int _imageRoomLight, 
	int _imageRoomDark, int[] _roomExitsId, String[] _roomExitsNames){

		this.thisRoomTrap =_thisRoomTrap;
		this.thisRoomMonster = _thisRoomMonster;
		this.thisRoomTreasure = _thisRoomTreasure;

		roomId = _roomId;
		roomName = _roomName;
		roomDescription = _roomDescription;

		imageRoomLight = _imageRoomLight;
		imageRoomDark = _imageRoomDark;
		
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
		//output += thisRoomTreasure.TreasureVisible() ? "\n You found a treasure on this room" : "\n You found no treasure on this room";

    	return output ;
	}

	public void RemapRoomImage(int _index) {
    	int tempImageRefLight = 0;
    	int tempImageRefDark = 0;

    	switch(_index){
    		case 0 :
    			tempImageRefLight = ImageReferences.imageCastle[Util.GenerateNumberBetween(0,ImageReferences.imageCastle.length)];
				tempImageRefDark = ImageReferences.imageCastleDark[Util.GenerateNumberBetween(0,ImageReferences.imageCastleDark.length)];
    			break;
			case 1 :
				tempImageRefLight = ImageReferences.imageDungeon[Util.GenerateNumberBetween(0,ImageReferences.imageDungeon.length)];
				tempImageRefDark = ImageReferences.imageDungeonDark[Util.GenerateNumberBetween(0,ImageReferences.imageDungeonDark.length)];
				break;
			case 2 :
				tempImageRefLight = ImageReferences.imageCatacomb[Util.GenerateNumberBetween(0,ImageReferences.imageCatacomb.length)];
				tempImageRefDark = ImageReferences.imageCatacombDark[Util.GenerateNumberBetween(0,ImageReferences.imageCatacombDark.length)];
				break;
			case 3 :
				tempImageRefLight = ImageReferences.imageSewer[Util.GenerateNumberBetween(0,ImageReferences.imageSewer.length)];
				tempImageRefDark = ImageReferences.imageSewerDark[Util.GenerateNumberBetween(0,ImageReferences.imageSewerDark.length)];
				break;
			case 4 :
				tempImageRefLight = ImageReferences.imageCave[Util.GenerateNumberBetween(0,ImageReferences.imageCave.length)];
				tempImageRefDark = ImageReferences.imageCaveDark[Util.GenerateNumberBetween(0,ImageReferences.imageCaveDark.length)];
				break;
			default:break;
    	}



    	imageRoomLight=tempImageRefLight;
		imageRoomDark=tempImageRefDark;
	}

	public int RoomImage(boolean _type){ 
		
		if(_type==false){
			return imageRoomDark;
		}else{
			return imageRoomLight;
		}
	}


	//ACTIONS----------
	public String GetActionsName(int indexActionButton) {
		if(indexActionButton==0) {
			if (thisRoomMonster.MonsterPresent() == true && thisRoomMonster.MonsterDead() == false) {
				return "Fight Foe";

			} else if (roomExplored == false) {

				return "Investigate";

			} else if (thisRoomTrap.TrapName() != "No Trap" && thisRoomTrap.TrapDetected() == true && thisRoomTrap.TrapDisarmed() == false) {
				return "Disarm Trap";

			} else if (thisRoomTreasure.TreasurePresent() == true && thisRoomTreasure.TreasureVisible() == true) {
				return "Open Treasure";
			}
		}


		if(indexActionButton==1) {
			if (true) {
				return "Map";

			}
		}


			return "No Action";

		//foes are automaticly detected you try to figth or escape if you try to escape to other room you may fail or and get damage
		//IF the room is investigated you may find traps or treasure
		//IF you detect a trap you may disarm it. 
		//IF not and you try to do any other action(move to other room or open treasure) (other then figth) you will activate the trap and get damage
		//IN the end you get always to pick the treasure if you have found it
	
	}

	public void DoActions (String _actionName){

		switch (_actionName){
			case "Fight Foe":
				this.thisRoomMonster.FightFoe();//fight foe;
			break;
			case "Investigate Room": 
				this.InvestigateRoom();	//looks for traps and treasures in room
			break;
			case "Disarm Trap":
				this.thisRoomTrap.DisarmTrap(Util.GenerateNumberBetween(1,13));//looks for traps and treasures in room
			break;
			case "Open Treasure":
				this.thisRoomTreasure.OpenTreasure() ;//looks for traps and treasures in room
			break;
			
			default: ;//get damaged if there is a foe or a trap
			break;
		}
		
		
	}

	private void InvestigateRoom(){
	 
		if(thisRoomTrap.TrapPresent()==true && thisRoomTrap.TrapDetected()==false && thisRoomTrap.TrapDisarmed()==false ){
			
		}
		this.roomExplored = true;
	}


	//EXPLORE-----------------------
	public boolean RoomExplored(){ return roomExplored;	}




	//---SET ID---
	public void RemapRoomId(){

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
	public void ExecuteTrapCommand(){


	}

	//MONSTER COMMANDS
	public void ExecuteMonsterCommand(){


	}
	//TREASURE COMMANDS
	public void ExecuteTreasureCommand(){


	}



}
