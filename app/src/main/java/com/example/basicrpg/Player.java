package com.example.basicrpg;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class Player {

    private static Player ourPlayer = new Player();

    static Player GetCurrentPlayer(){return ourPlayer;}

    private String playerName;

    private int playerHealth;
    private int playerMaxHealth;

    private int playerSanity;
    private int playerMaxSanity;
	
	private int playerDamage;
    
    private int playerArmor;
    private int playerHitDodge;

    private int playerDungeonLevelLocation;
    private int playerDungeonSectionLocation;
    private int playerDungeonRoomLocation;

    private int playerGold;

    private int playerSurvivedTurns;



    //private int playerDungeonRoomID;

    private DungeonRoom playerDungeonRoom = new DungeonRoom();

    private List<Integer> playerDungeonLevelLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonSectionLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonRoomLocationHistory = new ArrayList<Integer>();

    private List<Integer> playerDungeonRoomIDHistory = new ArrayList<Integer>();

    private List<Item> Inventory = new ArrayList<Item>();


    public void PlayerReset(){
        ourPlayer = new Player();
    }

    private Player(){
		
		playerMaxHealth = GameSettings.PLAYER_MAX_HEALTH;
		playerHealth = GameSettings.PLAYER_HEALTH;
		
		playerMaxSanity = GameSettings.PLAYER_MAX_SANITY;
		playerSanity = GameSettings.PLAYER_SANITY;
		
		playerDamage = GameSettings.PLAYER_DAMAGE;
		
		playerArmor = GameSettings.PLAYER_ARMOR;
		playerHitDodge = GameSettings.PLAYER_HIT_DODGE;

        playerGold = 0;
        playerSurvivedTurns = 0;


        SetPlayerDungeonLevelLocation(0);
        SetPlayerDungeonSectionLocation(0);
        SetPlayerDungeonRoomLocation(0);

        FindPlayerRoomByLocation();

        SetInvetory(4);
    }


    private void SetInvetory(int _quantityItems){

        Inventory.add(ItemsList.OldRustyKey());
        Inventory.add(ItemsList.MementoMori());

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

        if(playerHealth <= 0){

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

        SetPlayerHealth(0);//this is a modifier not the value per se
    }

    public int GetPlayerMaxHealth() {return playerMaxHealth;}
//--

   //PLAYERS SANITY
    public void	SetPlayerSanity(int _playerSanityModify){

        playerSanity += _playerSanityModify;

        if(playerSanity < 0){

            playerSanity = 0;
        }

        if(playerSanity <= 0){
            SetPlayerHealth(-5);
        }

        if(playerSanity > playerMaxSanity){

            playerSanity = playerMaxSanity;
        }

    }

    public int GetPlayerSanity(){return playerSanity;}

    //PLAYERS MAX SANITY
    public void	SetPlayerMaxSanity(int _playerMaxSanity){

        playerMaxSanity += _playerMaxSanity;

        if(playerMaxSanity < 1){

            playerMaxSanity = 1;

            SetPlayerSanity(playerSanity);
        }

        SetPlayerSanity(0);//this is a modifier not the value per se
    }

    public int GetPlayerMaxSanity() {return playerMaxSanity;}

    public int GetPlayerDamage() {return playerDamage;}

    public int GetPlayerHitDodge() {return playerHitDodge;}
    public int GetPlayerArmor() {return playerArmor;}

	//PLAYER COMBAT
	public int PlayerAttack(){
		
		if(IsIlluminated()){
			return playerDamage + Util.GenerateNumberBetween(0,6) + 1;
		}else{
			return ((int)(playerDamage/2)) + Util.GenerateNumberBetween(0,3) + 1;
		}
		
		//does not implement weapon damage modifier yet
	}
	
	public boolean PlayerDefends(int opponentHitDodge, int opponentAttack ) {
		
		if(IsIlluminated()){
			if(opponentHitDodge + Util.GenerateNumberBetween(0,6) + 1 > playerHitDodge + Util.GenerateNumberBetween(0,6) + 1){
				
				int damage = opponentAttack - playerArmor;
				damage = damage>0?0:damage;
				SetPlayerHealth(damage);
							
				return false;//you failed to defend
			}
		}else{
			if(opponentHitDodge + Util.GenerateNumberBetween(0,6) + 1 > ((int)(playerHitDodge/2)) + Util.GenerateNumberBetween(0,3) + 1){
				
				int damage = opponentAttack - playerArmor;
                damage = damage>0?0:damage;
				SetPlayerHealth(damage);
							
				return false;//you failed to defend
			}
			
		}
        return true; //you suceded to defend
	}

//--

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


	//OUTPUT
	public String PlayerStats(){
		String outputString ="";

        outputString +=PlayerIllumination();
        
		return outputString;
    }

	//INVENTORY
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
	
	
	//ILLUMINATION
	public String PlayerIllumination(){//DESCRIPTIVE OUTPUT
		int quantityIllumination = 0;
		
		for (Item i: Inventory) {
			if(i.IsIlluminator()){
				
				quantityIllumination++;
			}
			
		}
		String outputString = quantityIllumination+"";
        return outputString;
	}
	
	public void PlayerIlluminationDelete(int _delQuant){
		for(int j = 0; j<_delQuant; j++){
			for (int i = 0; i<Inventory.size(); i++) {
				if(Inventory.get(i).IsIlluminator()==true){
					Log.d("INVENTORY","Deleted "+ Inventory.get(i).GetItemName());
				    Inventory.remove(i);

					break;
				}
				
			}
		}
	}
	
	public boolean IsIlluminated(){
		boolean inLight = false;
		
		for (Item i: Inventory) {
			if(i.IsIlluminator()){
				inLight = true;
				break;
			}
			
		}
		
		return inLight;
	}

	//SCORING

    public void SetPlayerSurvivedTurns(){
        playerSurvivedTurns++;
    }


    public void SetPlayerGold(int _goldModifier){
        playerGold+=_goldModifier;
        if (playerGold<0){playerGold=0;}
    }

	public String GiveScore(){
        String _score = "You scored "+ playerSurvivedTurns*(playerHealth+1)*(playerSanity+1)*(playerGold+1);
        return _score;
    }
}