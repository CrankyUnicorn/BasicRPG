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
        outputString += " "+Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomID();
        outputString += "\n";
        outputString += Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomDescription();

        return outputString ;
    }

    public int GetDungeonRoomImage() {
        int dungeonImageView = Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomImage();
        return  dungeonImageView;
    }

    public String GetDoorButtonTitle(int _indexDoor){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitNameByIndex(_indexDoor);
    }


    public String GetCharacterStatsTitle(){

        return Player.GetCurrentPlayer().PlayerStats();
    }


    public String GetCharacterInventoryTitle(){

        return Player.GetCurrentPlayer().PlayerInventory();
    }



    public void SelectedDoorButton(int _roomID){


        int roomID = Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitIdByIndex(_roomID);
        Player.GetCurrentPlayer().SetPlayerDungeonRoomByID(roomID);


    }

}
