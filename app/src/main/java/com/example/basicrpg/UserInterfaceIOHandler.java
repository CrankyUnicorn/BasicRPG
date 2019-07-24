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
                                "| Section " + DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()
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

        int[] DoorsInfoIDs = Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomExitsId();

        int DoorInfoID  = DoorsInfoIDs.length > 0 ? DoorsInfoIDs[0]:0;

        return DoorInfoID;
    }

    public String GetDoorOneButtonTitle(){

        String[] DoorsInfos =    Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomExitsNames();

        String DoorInfo = DoorsInfos.length > 0 ? DoorsInfos[0]:"No Door";

        return DoorInfo ;
    }

    //#2
    public int GetDoorTwoButton() {

        int[] DoorsInfoIDs = Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomExitsId();

        int DoorInfoID  = DoorsInfoIDs.length > 1 ? DoorsInfoIDs[1]:0;

        return DoorInfoID;
    }

    public String GetDoorTwoButtonTitle(){

        String[] DoorsInfos =    Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomExitsNames();

        String DoorInfo = DoorsInfos.length > 1 ? DoorsInfos[1]:"No Door";

        return DoorInfo ;
    }

    //#3
    public int GetDoorThreeButton() {

        int[] DoorsInfoIDs = Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomExitsId();

        int DoorInfoID  = DoorsInfoIDs.length > 2 ? DoorsInfoIDs[2]:0;

        return DoorInfoID;
    }

    public String GetDoorThreeButtonTitle(){

        String[] DoorsInfos =    Player.GetCurrentPlayer().GetPlayerDungeonRoom().RoomExitsNames();

        String DoorInfo = DoorsInfos.length > 2 ? DoorsInfos[2]:"No Door";

        return DoorInfo ;
    }

    public String GetCharacterStatsTitle(){return "nothing yet" ;}


    public String GetCharacterInventoryTitle(){return "nothing yet";}


    public void SelectedDoorButton(int _roomID){
        switch (_roomID){
            case 0 :     Player.GetCurrentPlayer().SetPlayerDungeonRoomLocation(GetDoorOneButton());
                        break;
            case 1 :
                        Player.GetCurrentPlayer().SetPlayerDungeonRoomLocation(GetDoorTwoButton());
                        break;
            case 2 :
                        Player.GetCurrentPlayer().SetPlayerDungeonRoomLocation(GetDoorThreeButton());
                        break;
            default :
                        break;
        }
    }

}
