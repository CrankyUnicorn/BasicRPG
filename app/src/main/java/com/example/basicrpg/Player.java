package com.example.basicrpg;

public class Player {


    private String playerName;

    private int playerHealth;
    private int playerMaxHealth;
    private int playerSanity;

    private int playerDungeonLevelLocation;
    private int playerDungeonSectionLocation;
    private int playerDungeonRoomLocation;

    private int[] playerDungeonLevelLocationHistory;
    private int[] playerDungeonSectionLocationHistory;
    private int[] playerDungeonRoomLocationHistory;

    //private List<Item> Inventory = new ArrayList<Item>();


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

    public int GetPlayerHealth(){

        return playerHealth;
    }

    //PLAYERS MAX HEALTH
    public void	SetPlayerMaxHealth(int _playerMaxHealth){

        playerMaxHealth += _playerMaxHealth;

        if(playerMaxHealth < 1){

            playerMaxHealth = 1;

            SetPlayerHealth(playerHealth);
        }

        SetPlayerHealth(0);
    }

    public int GetPlayerMaxHealth() {

        return playerMaxHealth;

    }

}