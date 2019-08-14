/*
* BASIC RPG - DUNGEON CRAWLER
* CRANKYUNICORN 2019
* azedo.peter@gmail.com
*/

package com.example.basicrpg;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInterfaceIOHandler {

    private static final UserInterfaceIOHandler ourInstance = new UserInterfaceIOHandler();

    public static UserInterfaceIOHandler GetUserInterfaceIOHandler() {
        return ourInstance;
    }

	public static boolean overlayWindowOpen = false;


    //********************** OUTPUT ***************************************************************

    //LOCATION -----------------------
    public String GetDungeonNameTitle(){return DungeonMap.GetDungeonMap().GetCurrentDungeon().GetDungeonName();}


    public String GetDungeonLocationTitle(){

        String LocationInfo =  Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomName() + " | " + " Room no." + Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomID();

        return LocationInfo;
    }


    //IMAGES ------------------------------
    public int GetDungeonRoomImage() {
		int dungeonImageView;
		
		if(Player.GetCurrentPlayer().IsIlluminated()==false){
		
			dungeonImageView = Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomImage(false);
		
		}else{
		
			dungeonImageView = Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomImage(true);
        	
		}
        return  dungeonImageView;
    }

    public int GetDungeonRoomImageFoe() {
        return  Player.GetCurrentPlayer().GetPlayerDungeonRoom().ThisRoomMonster().MonsterImage();
    }


    public int GetDungeonRoomImageEffect(){//REWORK THIS PART

        return  0;
    }


    public int GetDungeonRoomImageChar() {
        if(Player.GetCurrentPlayer().IsIlluminated()!=true){
            return ImageReferences.imageCharacter[0];
        }else{
            return ImageReferences.imageCharacter[1];
        }
    }


    //STATS ---------------------------------------------
    public String GetCharacterIlluminationTitle(){

        return Player.GetCurrentPlayer().PlayerIlluminationQuantity();
    }


    //ROOM DESCRIPTION ---------------------------------------------
    public String GetDungeonRoomDescriptionTitle(){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomDescriptionOutput();
    }


    //BUTTONS ------------------------------------------
    public String GetDoorButtonTitle(int _indexDoor){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitNameByIndex(_indexDoor);
    }

    public String GetACtivityButtonTitle(int _indexActionButton){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetActionsName(_indexActionButton);
    }


	//OVERLAY WINDOW -----------------------------------
	
	public boolean GetOverlayWindow(){
		if(overlayWindowOpen){
			return true;

		}else{
			return false;
		}
	}


    public String GetOverlayText(){
		String _output = "";
		if(overlayWindowOpen){
            _output +="Level "+ DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()
                    .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetDungeonLevelName() +
                    " | Section " + DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()
                    .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildSections()
                    .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetDungeonSectionName();
			_output += "\n\n" ;
			_output += Player.GetCurrentPlayer().GetPlayerJournal();
            _output += "\n\n" ;
			_output += Player.GetCurrentPlayer().PlayerInventory();
			return _output;
		}else{
			return _output;
		}
	}

	

    //ACTIONS ************************************************************************************

    // WHEN BUTTONS ARE PRESSED----------------------
    public void SelectedDoorButton(int _doorToID){

        int nextRoomID = Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitIdByIndex(_doorToID);

			if(Player.GetCurrentPlayer().GetPlayerDungeonRoom().ThisRoomMonster().FleeFoe()){//if flee sucessfull or no foe or dead foe
				
				if(!Player.GetCurrentPlayer().GetPlayerDungeonRoom().ThisRoomTrap().TriggerTrap()){
                    Player.GetCurrentPlayer().SetPlayerDungeonRoomByID(nextRoomID);

                }

			}

			GameLoop();

    }

    public void SelectedActionButton(String _actionName){

		if(_actionName == GameSettings.PLAYER_ROOM_ACTIONS[5]){
			if(overlayWindowOpen == false){
			
				overlayWindowOpen = true;
                Player.GetCurrentPlayer().GetPlayerDungeonRoom().DoActions(_actionName);//DOES NOTHING

			}else{
				
				overlayWindowOpen = false;

			}
		}else{

            Player.GetCurrentPlayer().GetPlayerDungeonRoom().DoActions(_actionName);//Deals all actions other then journal
			GameLoop();
		
		}
		
      
    }


    //############### GAME LOOP #########################
    private void GameLoop(){
		
		//Player.GetCurrentPlayer().SetPlayerJournal("You survided another turn");

        Player.GetCurrentPlayer().SetPlayerSurvivedTurns();//sets a new survived turn for scoring

        Player.GetCurrentPlayer().PlayerIlluminationDelete(-1);//burns a light source per turn

        Player.GetCurrentPlayer().SanityCheck();//checks if there is light if not deletes sanity


        //everytime that any button is pressed the flame  should go down a bit
        //every turn with out a light sanity goes down a bit
        //everytime you find a foe or a bad item sanity goes down in case of bad item the quantity depends on the quality of the item
        //when sanity reaches 0 you go insane and wonder while loosing life you can find a tourch by luck
        //you deal half the damage and receive double damage in darkness
        //you score for every turn aka press of a button you area able to make, some items may give extra score
    }


}
