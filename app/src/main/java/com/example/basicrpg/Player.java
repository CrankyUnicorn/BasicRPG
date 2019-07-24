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

    private DungeonRoom playerDungeonRoom = new DungeonRoom();

    private List<Integer> playerDungeonLevelLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonSectionLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonRoomLocationHistory = new ArrayList<Integer>();

    //private List<Item> Inventory = new ArrayList<Item>();
    private Player(){
        SetPlayerDungeonLevelLocation(0);
        SetPlayerDungeonSectionLocation(0);
        SetPlayerDungeonRoomLocation(0);
        GetPlayerRoom();
    }

    private void GetPlayerRoom(){
        playerDungeonRoom = DungeonMap.GetDungeonMap().GetCurrentDungeon().GetChildLevels()
                .get(playerDungeonLevelLocation).GetChildSections()
                .get(playerDungeonSectionLocation).GetChildRooms()
                .get(playerDungeonRoomLocation);

    }

    //ACESSORS
    //PLAYER NAME
    public void SetPlayerName(String _playerName){

        if(playerName == null){
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

    //PLAYER AT REAL OBJECT ROOM
    public DungeonRoom GetPlayerDungeonRoom(){
        GetPlayerRoom();
        return playerDungeonRoom;
    }


}