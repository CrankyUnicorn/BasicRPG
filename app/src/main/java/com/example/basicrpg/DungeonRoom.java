/*
* BASIC RPG - DUNGEON CRAWLER
* CRANKYUNICORN 2019
* azedo.peter@gmail.com
*/

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

	public String RoomDescription(){ return roomDescription; }

	public String RoomDescriptionOutput(){
    	String output="";

		output += roomDescription;
		output += "\n";
		output += thisRoomMonster.MonsterPresent() ? thisRoomMonster.MonsterDead() ? "There is a dead body on the ground. " :"": "";
		output += thisRoomTrap.TrapDetected() ? thisRoomTrap.TrapDisarmed() ? "" : "You found a trap on this room. " : "";
		output += thisRoomTreasure.TreasureVisible() ? "You found a treasure on this room. " : "";
		output += "\n";
		output += Player.GetCurrentPlayer().GetPlayerMiniJournal();

    	return output ;
	}

	public void RemapRoomImage(int _index) {
    	int tempImageRefLight = 0;
    	int tempImageRefDark = 0;
    	int tempImageRefNumb = 0;

    	switch(_index){
    		case 0 :
				tempImageRefNumb = Util.GenerateNumberBetween(0,ImageReferences.imageCastle.length);
    			tempImageRefLight = ImageReferences.imageCastle[tempImageRefNumb];
				tempImageRefDark = ImageReferences.imageCastleDark[tempImageRefNumb];
    			break;
			case 1 :
				tempImageRefNumb = Util.GenerateNumberBetween(0,ImageReferences.imageDungeon.length);
				tempImageRefLight = ImageReferences.imageDungeon[tempImageRefNumb];
				tempImageRefDark = ImageReferences.imageDungeonDark[tempImageRefNumb];
				break;
			case 2 :
				tempImageRefNumb = Util.GenerateNumberBetween(0,ImageReferences.imageCatacomb.length);
				tempImageRefLight = ImageReferences.imageCatacomb[tempImageRefNumb];
				tempImageRefDark = ImageReferences.imageCatacombDark[tempImageRefNumb];
				break;
			case 3 :
				tempImageRefNumb = Util.GenerateNumberBetween(0,ImageReferences.imageSewer.length);
				tempImageRefLight = ImageReferences.imageSewer[tempImageRefNumb];
				tempImageRefDark = ImageReferences.imageSewerDark[tempImageRefNumb];
				break;
			case 4 :
				tempImageRefNumb = Util.GenerateNumberBetween(0,ImageReferences.imageCave.length);
				tempImageRefLight = ImageReferences.imageCave[tempImageRefNumb];
				tempImageRefDark = ImageReferences.imageCaveDark[tempImageRefNumb];
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


	//ACTIONS ****************************************************************************
	public String GetActionsName(int indexActionButton) {
		if(indexActionButton==0) {
			if (thisRoomMonster.MonsterPresent() == true && thisRoomMonster.MonsterDead() == false) {
				return GameSettings.PLAYER_ROOM_ACTIONS[1];//"Fight Foe"

			} else if (roomExplored == false) {

				return GameSettings.PLAYER_ROOM_ACTIONS[2];//"Investigate"

			} else if (thisRoomTrap.TrapName() != "No Trap" && thisRoomTrap.TrapDetected() == true && thisRoomTrap.TrapDisarmed() == false) {
				return GameSettings.PLAYER_ROOM_ACTIONS[3];//"Disarm Trap"

			} else if (thisRoomTreasure.TreasureName() != "No Treasure" && thisRoomTreasure.TreasurePresent() == true && thisRoomTreasure.TreasureVisible() == true && thisRoomTreasure.GetItem()!=null) {
				return GameSettings.PLAYER_ROOM_ACTIONS[4];//"Open Treasure"
			}else{
				return GameSettings.PLAYER_ROOM_ACTIONS[0];//"No actions"
			}
		}


		if(indexActionButton==1) {
			if (true) {
				return GameSettings.PLAYER_ROOM_ACTIONS[5];

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

		if (GameSettings.PLAYER_ROOM_ACTIONS[1]==_actionName){
			this.thisRoomMonster.FightFoe();//fight foe;
		}else
		if (GameSettings.PLAYER_ROOM_ACTIONS[2]==_actionName){
			this.InvestigateRoom();	//looks for traps and treasures in room
		}else
		if (GameSettings.PLAYER_ROOM_ACTIONS[3]==_actionName){
			this.thisRoomTrap.DisarmTrap();//looks for traps and treasures in room
		}else
		if (GameSettings.PLAYER_ROOM_ACTIONS[4]==_actionName){
			this.thisRoomTreasure.OpenTreasure();//looks for traps and treasures in room
		}else
		if (GameSettings.PLAYER_ROOM_ACTIONS[5]==_actionName){}//journal
		
	}

	private void InvestigateRoom(){
	 	Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_INVESTIGATE");
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_INVESTIGATE");
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_INVESTIGATE");
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

		Player.GetCurrentPlayer().SetPlayerJournal("You investigated the room");

				this.thisRoomTrap.DetectTrap();
				this.thisRoomTreasure.FindTreasure();
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

	public void SetRoomExitsIds(int[] _ids){

		roomExitsId = _ids;
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
