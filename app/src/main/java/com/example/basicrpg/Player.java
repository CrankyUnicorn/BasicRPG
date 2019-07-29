package com.example.basicrpg;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

class Player {

    private static final Player ourPlayer = new Player();

    static Player GetCurrentPlayer(){return ourPlayer;}

    private String playerName;

    private int playerHealth;
    private int playerMaxHealth;

    private int playerSanity;
    private int playerMaxSanity;

    private int playerDungeonLevelLocation;
    private int playerDungeonSectionLocation;
    private int playerDungeonRoomLocation;

    //private int playerDungeonRoomID;

    private DungeonRoom playerDungeonRoom = new DungeonRoom();

    private List<Integer> playerDungeonLevelLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonSectionLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonRoomLocationHistory = new ArrayList<Integer>();

    private List<Integer> playerDungeonRoomIDHistory = new ArrayList<Integer>();

    private List<Item> Inventory = new ArrayList<Item>();


    private Player(){
        SetPlayerDungeonLevelLocation(0);
        SetPlayerDungeonSectionLocation(0);
        SetPlayerDungeonRoomLocation(0);

        FindPlayerRoomByLocation();

        SetInvetory(2);
    }

    private void SetInvetory(int _quantityItems){

        Inventory.add(ItemsList.KeyItem());

        for (int i = 0; i < _quantityItems ; i++) {
            Item item = ItemsList.GetRandomItem();


            Inventory.add(item);
        }


    }

    private void FindPlayerRoomByLocation(){
        playerDungeonRoom = DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()
                .get(playerDungeonLevelLocation).GetChildSections()
                .get(playerDungeonSectionLocation).GetChildRooms()
                .get(playerDungeonRoomLocation);


    }

    /*private void FindPlayerRoomLocationByID(){
        int nNum=0;
        int mNum=0;
        int lNum=0;
        for (DungeonLevel n : DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()) {

            for (DungeonSection m : n.GetChildSections()) {

                for (DungeonRoom l : m.GetChildRooms()) {

                    if(l.RoomID()==playerDungeonRoom.RoomID()){
                        playerDungeonLevelLocation =nNum;
                        playerDungeonSectionLocation =mNum;
                        playerDungeonRoomLocation =lNum;
                        playerDungeonRoom = l;
                    }

                    lNum++;
                }
                lNum = 0;
                mNum++;
            }
            mNum = 0;
            nNum++;
        }

    }*/

    private void FindPlayerRoomByID(int _roomID){
        int nNum=0;
        int mNum=0;
        int lNum=0;
        for (DungeonLevel n : DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()) {

            for (DungeonSection m : n.GetChildSections()) {

                for (DungeonRoom l : m.GetChildRooms()) {

                    if(l.RoomID()==_roomID){
                        playerDungeonLevelLocation =nNum;
                        playerDungeonSectionLocation =mNum;
                        playerDungeonRoomLocation =lNum;
                        playerDungeonRoom = l;
                    }

                    lNum++;
                }
                lNum = 0;
                mNum++;
            }
            mNum = 0;
            nNum++;
        }

    }

    //ACESSORS

    //PLAYER AT REAL OBJECT ROOM
  /*  public void SetPlayerDungeonRoom(DungeonRoom _room){
        playerDungeonRoom = _room;
        FindPlayerRoomLocationByID();

    }*/

    public DungeonRoom GetPlayerDungeonRoom(){
        FindPlayerRoomByLocation();//just for safety
        return playerDungeonRoom;
    }


    //PLAYER NAME
    public void SetPlayerName(String _playerName){

        if(playerName.isEmpty()){
            playerName = _playerName;
        }

    }

    public String GetPlayerName(){return playerName;}


    //PLAYERS HEALTH
    public void	SetPlayerHealth(int _playerHealthModify){

        playerHealth += _playerHealthModify;

        if(playerHealth < 0){

            playerHealth = 0;
        }

        if(playerHealth > playerMaxHealth){

            playerHealth = playerMaxHealth;
        }

    }

    public int GetPlayerHealth(){return playerHealth;}

    //PLAYERS MAX HEALTH
    public void	SetPlayerMaxHealth(int _playerMaxHealth){

        playerMaxHealth += _playerMaxHealth;

        if(playerMaxHealth < 1){

            playerMaxHealth = 1;

            SetPlayerHealth(playerHealth);
        }

        SetPlayerHealth(0);
    }

    public int GetPlayerMaxHealth() {return playerMaxHealth;}


    //PLAYER LEVEL
    public void SetPlayerDungeonLevelLocation(int _level){
        playerDungeonLevelLocation = _level;
        playerDungeonLevelLocationHistory.add(_level);
    }
    public int GetPlayerDungeonLevelLocation(){return playerDungeonLevelLocation;}


    //PLAYER SECTION
    public void SetPlayerDungeonSectionLocation(int _section){
        playerDungeonSectionLocation = _section;
        playerDungeonSectionLocationHistory.add(_section);
    }
    public int GetPlayerDungeonSectionLocation(){return playerDungeonSectionLocation;}


    //PLAYER ROOM
    public void SetPlayerDungeonRoomLocation(int _room){
        playerDungeonRoomLocation = _room;
        playerDungeonRoomLocationHistory.add(_room);
    }
    public int GetPlayerDungeonRoomLocation(){return playerDungeonRoomLocation;}

    //PLAYER ROOM ID
    public void SetPlayerDungeonRoomByID(int _roomID){
        FindPlayerRoomByID(_roomID);
        playerDungeonRoomIDHistory.add(_roomID);
    }

    public int GetPlayerDungeonRoomID(){
        FindPlayerRoomByLocation();//again just to be safe
        return playerDungeonRoom.RoomID();
    }


public String PlayerStats(){
    String outputString ="";
    outputString ="Player Name" + GetPlayerName();
    outputString =" | ";
    outputString ="Player Health " + GetPlayerHealth();
        return outputString;
    }

public String PlayerInventory(){
    String outputString ="";
    for (Item i: Inventory) {
        outputString += i.GetItemName();
        outputString += " - ";
        outputString += i.GetItemDescription();
        outputString += "\n";
    }
        return outputString;
}

}