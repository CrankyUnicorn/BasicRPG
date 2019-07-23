package com.example.basicrpg;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInterfaceIOHandler {

    private static final UserInterfaceIOHandler ourInstance = new UserInterfaceIOHandler();
    public static UserInterfaceIOHandler GetUserInterfaceIOHandler() {
        return ourInstance;
    }


    public String GetDungeonNameTitle(){return DungeonMap.GetCurrentDungeon().GetDungeonName();}


    public String GetDungeonLocationTitle(){

        String LocationInfo =  "Level "+ DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetDungeonLevelName() +
                                "| Section " + DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetDungeonSectionName();

        return LocationInfo;
    }


    public String GetDungeonRoomDescriptionTitle(){

        String RoomInfo =   DungeonMap.GetCurrentDungeon().GetChildList()
                            .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                            .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                            .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomName() +
                            " | "+DungeonMap.GetCurrentDungeon().GetChildList()
                            .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                            .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                            .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomDescription();
        return RoomInfo ;
    }

    //dungeonImageView = (ImageView) findViewById(R.id.dungeonImageView);

    private int doorOneButton;
    public String GetDoorOneButtonTitle(){

        String[] DoorsInfo =    DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                                .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomExitsNames();

        int[] DoorsInfoNumber =  DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                                .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomExitsId();

        String DoorInfo = DoorsInfo.length > 0 ? DoorsInfo[0]:"No Door";
        int DoorInfoNumber = DoorsInfoNumber.length > 0 ? DoorsInfoNumber[0]:0;

        return DoorInfo ;
    }


    private int doorTwoButton;
    public String GetDoorTwoButtonTitle(){

        String[] DoorsInfo =    DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                                .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomExitsNames();
        int[] DoorsInfoNumber =  DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                                .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomExitsId();

        String DoorInfo = DoorsInfo.length > 1 ? DoorsInfo[1]:"No Door";
        int DoorInfoNumber = DoorsInfoNumber.length > 1 ? DoorsInfoNumber[1]:0;

        return DoorInfo ;
    }

    private int doorThreeButton;
    public String GetDoorThreeButtonTitle(){

        String[] DoorsInfo =    DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                                .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomExitsNames();
        int[] DoorsInfoNumber =  DungeonMap.GetCurrentDungeon().GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonLevelLocation()).GetChildList()
                                .get( Player.GetCurrentPlayer().GetPlayerDungeonSectionLocation()).GetChildList()
                                .get(Player.GetCurrentPlayer().GetPlayerDungeonRoomLocation()).RoomExitsId();

        String DoorInfo = DoorsInfo.length > 2 ? DoorsInfo[2]:"No Door";
        int DoorInfoNumber = DoorsInfoNumber.length > 2 ? DoorsInfoNumber[2]:0;

        return DoorInfo ;
    }

    public String GetCharacterStatsTitle(){return "nothing yet" ;}


    public String GetCharacterInventoryTitle(){return "nothing yet";}


    public void SelectedDoorButton(int _roomID){
        switch (_roomID){
            case 0 : Player.GetCurrentPlayer().SetPlayerDungeonRoomLocation(doorOneButton); break;
            case 1 : Player.GetCurrentPlayer().SetPlayerDungeonRoomLocation(doorTwoButton); break;
            case 2 : Player.GetCurrentPlayer().SetPlayerDungeonRoomLocation(doorThreeButton); break;
            default : break;
        }
    }

}
