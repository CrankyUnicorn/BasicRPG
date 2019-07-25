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

        String RoomInfo =   Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomDescription();
        return RoomInfo ;
    }


    //dungeonImageView = (ImageView) findViewById(R.id.dungeonImageView);


    //#1
    public int GetDoorOneButton() {

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitsIdByIndex(0);
    }

    public String GetDoorOneButtonTitle(){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitsNamesByIndex(0);
    }

    //#2
    public int GetDoorTwoButton() {

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitsIdByIndex(1);
    }

    public String GetDoorTwoButtonTitle(){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitsNamesByIndex(1);
    }

    //#3
    public int GetDoorThreeButton() {

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitsIdByIndex(2);
    }

    public String GetDoorThreeButtonTitle(){

        return Player.GetCurrentPlayer().GetPlayerDungeonRoom().GetRoomExitsNamesByIndex(2);
    }


    public String GetCharacterStatsTitle(){return "nothing yet" ;}


    public String GetCharacterInventoryTitle(){return "nothing yet";}



    public void SelectedDoorButton(int _roomID){
        switch (_roomID){
            case 0 :    Player.GetCurrentPlayer().SetPlayerDungeonRoomID(GetDoorOneButton());

                        break;
            case 1 :
                        Player.GetCurrentPlayer().SetPlayerDungeonRoomID(GetDoorTwoButton());
                        break;
            case 2 :
                        Player.GetCurrentPlayer().SetPlayerDungeonRoomID(GetDoorThreeButton());
                        break;
            default :
                        break;
        }
        Player.GetCurrentPlayer().GetPlayerRoomLocationByID();
    }

}
