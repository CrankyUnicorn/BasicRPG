package com.example.basicrpg;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final Player ourPlayer = new Player();

    public static Player GetCurrentPlayer(){return ourPlayer;}

    private String playerName;

    private int playerHealth;
    private int playerMaxHealth;

    private int playerSanity;
    private int playerMaxSanity;

    private int playerDungeonLevelLocation;
    private int playerDungeonSectionLocation;
    private int playerDungeonRoomLocation;

    private List<Integer> playerDungeonLevelLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonSectionLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonRoomLocationHistory = new ArrayList<Integer>();

    //private List<Item> Inventory = new ArrayList<Item>();
    Player(){
        SetPlayerDungeonRoomLocation(1);
    }

    //ACESSORS
    //PLAYER NAME
    public void SetPlayerName(String _playerName){

        if(playerName == null){
            playerName = _playerName;
        }

    }

    public String GetPlayerName(){

        return playerName;
    }


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

    //
    public void SetPlayerDungeonLevelLocation(int _level){
        playerDungeonLevelLocation = _level;
        playerDungeonLevelLocationHistory.add(_level);
    }
    public int GetPlayerDungeonLevelLocation(){return playerDungeonLevelLocation;}

    //
    public void SetPlayerDungeonSectionLocation(int _section){
        playerDungeonSectionLocation = _section;
        playerDungeonSectionLocationHistory.add(_section);
    }
    public int GetPlayerDungeonSectionLocation(){return playerDungeonSectionLocation;}

    //
    public void SetPlayerDungeonRoomLocation(int _room){
        playerDungeonRoomLocation = _room;
        playerDungeonRoomLocationHistory.add(_room);
    }
    public int GetPlayerDungeonRoomLocation(){return playerDungeonRoomLocation;}


}