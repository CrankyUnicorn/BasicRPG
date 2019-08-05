package com.example.basicrpg;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInterfaceIOHandler {

    private static final UserInterfaceIOHandler ourInstance = new UserInterfaceIOHandler();
    public static UserInterfaceIOHandler GetUserInterfaceIOHandler() {
        return ourInstance;
    }



    public String GetDungeonNameTitle(){return DungeonMap.GetDungeonMap().GetCurrentDungeon().GetDungeonName();}


    public String GetDungeonLocationTitle(){

        String LocationInfo =  "Level "+ DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetDungeonLevelName() +
                                " | Section " + DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildSections()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetDungeonSectionName();

        return LocationInfo;
    }


    public String GetDungeonRoomDescriptionTitle(){
        String outputString = "";
        outputString += Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomName();
        outputString += " Room no."+Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomID();
        outputString += "\n";
        outputString += Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomDescription();

        return outputString ;
    }

    //IMAGES------------------------------
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

    public int GetDungeonRoomImageChar() {
        if(Player.GetCurrentPlayer().IsIlluminated()!=true){
           return ImageReferences.imageCharacter[0];
        }else{
            return ImageReferences.imageCharacter[1];
        }
    }


    //BUTTONS------------------------------------------
       public String GetDoorButtonTitle(int _indexDoor){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitNameByIndex(_indexDoor);
    }

    public String GetACtivityButtonTitle(int _indexActionButton){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetActionsName(_indexActionButton);
    }


    //STATS---------------------------------------------
    public String GetCharacterStatsTitle(){

        return Player.GetCurrentPlayer().PlayerStats();
    }


    public String GetCharacterInventoryTitle(){

        return Player.GetCurrentPlayer().PlayerInventory();
    }


    //##################################################################
    // WHEN BUTTONS ARE PRESSED----------------------
    public void SelectedDoorButton(int _roomID){


        int roomID = Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitIdByIndex(_roomID);
        Player.GetCurrentPlayer().SetPlayerDungeonRoomByID(roomID);

        GameLoop();
    }

    public void SelectedActionButton(String _actionName){


        Player.GetCurrentPlayer().GetPlayerDungeonRoom().DoActions(_actionName);

        GameLoop();
    }


    //############### GAME LOOP #########################
    private void GameLoop(){//should its own class

        Player.GetCurrentPlayer().SetPlayerSurvivedTurns();//sets a new survived turn for scoring

        Player.GetCurrentPlayer().PlayerIlluminationDelete(1);//deletes a light source

        if(Player.GetCurrentPlayer().IsIlluminated()!=true){
            Player.GetCurrentPlayer().SetPlayerSanity(-1);//removes one sanity if there is no lighting
        }


        if(Player.GetCurrentPlayer().GetPlayerDungeonRoom().ThisRoomMonster().MonsterPresent()==true){
            if(Player.GetCurrentPlayer().GetPlayerDungeonRoom().ThisRoomMonster().MonsterDead()==false){
                Player.GetCurrentPlayer().GetPlayerDungeonRoom().ThisRoomMonster().FightFoe();//fight a monster inside of room
                //PRINT EVENT TO VIEW
            }
        }



        //everytime that any button is pressed the flame  should go down a bit
        //every turn with out a light sanity goes down a bit
        //everytime you find a foe or a bad item sanity goes down in case of bad item the quantity depends on the quality of the item
        //when sanity reaches 0 you go insane and wonder while loosing life you can find a tourch by luck
        //you deal half the damage and receive double damage in darkness
        //you score for every turn aka press of a button you area able to make, some items may give extra score
    }


}
