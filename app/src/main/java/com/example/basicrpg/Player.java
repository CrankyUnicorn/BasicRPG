/*
* BASIC RPG - DUNGEON CRAWLER
* CRANKYUNICORN 2019
* azedo.peter@gmail.com
*/

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

    private int playerGold;

    private int playerSurvivedTurns;


    private int playerDungeonLevelLocation;
    private int playerDungeonSectionLocation;
    private int playerDungeonRoomLocation;


    private DungeonRoom playerDungeonRoom = new DungeonRoom();


    private List<Integer> playerDungeonLevelLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonSectionLocationHistory = new ArrayList<Integer>();
    private List<Integer> playerDungeonRoomLocationHistory = new ArrayList<Integer>();

    private List<Integer> playerDungeonRoomIDHistory = new ArrayList<Integer>();


    private List<Item> Inventory = new ArrayList<Item>();

    private List<String> playerJournal = new ArrayList<String>();

    private List<String> playerLastTurnActions = new ArrayList<String>();



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

        SetInventory(4);
    }





    //RESETS PLAYER
    public void PlayerReset(){
        ourPlayer = new Player();
    }



    //ACESSORS *************************************

    //PLAYER OBJECT ROOM
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

    //--

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
            SetPlayerHealth(-Util.GenerateNumberBetween(1,6));
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

    public void SanityCheck(){
        if(IsIlluminated()!=true){
            SetPlayerSanity(-1);//removes one sanity if there is no lighting
        }
    }

    public int GetPlayerMaxSanity() {return playerMaxSanity;}


    //COMBAT RELATED
    public int GetPlayerDamage() {return playerDamage;}

    public int GetPlayerHitDodge() {return playerHitDodge;}

    public int GetPlayerArmor() {return playerArmor;}


	//PLAYER COMBAT ************************************************************************
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
				damage = damage<0?0:damage;
				
				SetPlayerHealth(-damage);


                for (int i=0; i<damage;i++) {
                    //Log.d("ANIMATION"," Hit");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_HIT");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                }
							
				damageDescription(damage);
				return false;//you failed to defend
			}
		}else{
			if(opponentHitDodge + Util.GenerateNumberBetween(0,6) + 1 > ((int)(playerHitDodge/2)) + Util.GenerateNumberBetween(0,3) + 1){
				
				int damage = opponentAttack - playerArmor;
                damage = damage<0?0:damage;
				
				SetPlayerHealth(-damage);

                for (int i=0; i<damage;i++) {
                    //Log.d("ANIMATION","Monster Hit");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_HIT");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                }


                damageDescription(damage);
				return false;//you failed to defend
			}
			
		}
        Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
        Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DODGE");
        Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
        return true; //you suceded to defend
	}


    private void damageDescription(int _valuedamage){
        String _ouput = "";
        switch(_valuedamage){

            case 0 : _ouput = "but it dealt no damage"; break;
            case 1 : _ouput = "causing you a scratch"; break;
            case 2 : _ouput = "causing you a minor injury"; break;
            case 3 :_ouput = "causing you a injury"; break;
            case 4 : _ouput = "causing you a great wound"; break;
            default : _ouput = "causing you a brutal bleeding"; break;

        }
        Player.GetCurrentPlayer().SetPlayerJournal("You got hit by the foe " + _ouput);

    }

    //--

    //PLAYER LOCATION *******************************************
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


	//OUTPUT ****************************************************************************
    //ILLUMINATION
	public String PlayerIlluminationQuantity(){
		String outputString ="";

        outputString +=PlayerIllumination();
        
		return outputString;
    }

    public String PlayerIllumination(){//DESCRIPTIVE OUTPUT
        int quantityIllumination = 0;

        for (Item i: Inventory) {
            if(i.IsIlluminator()){

                quantityIllumination += i.getItemQuantity();
            }

        }
        String outputString = String.valueOf(quantityIllumination);
        return outputString;
    }

    public void PlayerIlluminationDelete(int _delQuant){
            for (int i = 0; i<Inventory.size(); i++) {

                if(Inventory.get(i).IsIlluminator()==true){

                    Log.d("INVENTORY","Deleted "+ Inventory.get(i).GetItemName());

                    Inventory.get(i).setItemQuantity(_delQuant);

                    if (Inventory.get(i).getItemQuantity()<=0){
                        Inventory.remove(i);
                    }

                    break;
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


	//INVENTORY

    private void SetInventory(int _quantityItems){

        Inventory.add(ItemsList.OldRustyKey());

        for (int i = 0; i < _quantityItems ; i++) {
            Item item = ItemsList.GetRandomItem();


            Inventory.add(item);
        }

        ReviewInventory();
    }


    private void ReviewInventory(){

        for(int i = 0; i<Inventory.size() ;i++){
            for(int j = 0; j<Inventory.size() ;j++){
                if (Inventory.get(i).GetItemName() == Inventory.get(j).GetItemName() && i!=j){
                    Inventory.get(i).setItemQuantity(Inventory.get(j).getItemQuantity());
                    Inventory.remove(j);
                    j--;
                    Log.d("INENTORY CHEK","item merged and deleted");
                }
            }
        }

        for(int j = 0; j<Inventory.size() ;j++){
            if (Inventory.get(j).GetItemName() == "No Item"){
                Inventory.remove(j);
                j--;
            }
        }

    }

	public String PlayerInventory(){
		String outputString ="";
		for (Item i: Inventory) {
            outputString += i.getItemQuantity() + "X ";
			outputString += i.GetItemName();
			outputString += " - ";
			outputString += i.GetItemDescription();
			outputString += "\n";
		}
        return outputString;
	}
	
	public void AddToPlayerInventory(Item _item){
        boolean itemExist = false;

        for(Item __item : Inventory){
            if (__item.GetItemName() == _item.GetItemName()){
                __item.setItemQuantity(_item.getItemQuantity());
                itemExist = true;
                break;
            }
        }

        if (!itemExist){
            Inventory.add(_item);
        }

        ReviewInventory();

	}

	public void RemoveFromPlayerInventory(Item _item ){
		Inventory.remove(_item);
	}

	public Item GetItemInInventory(int _index){return Inventory.get(_index);}


	//JOURNAL
	public void SetPlayerJournal(String _message)
    {
		playerJournal.add(_message + "\n");

	}

	public String GetPlayerJournal(){
		String journal="";

		for (int i = playerJournal.size()-1; i>=playerJournal.size()-21 && i>=0 ; i-- ){
		    if ( playerJournal.size()!=0) {
                journal += playerJournal.get(i);
            }
        }
		return journal;
	}

    public String GetPlayerMiniJournal(){
        String journal="";

        for (int i = playerJournal.size()>3?playerJournal.size()-3:0  ; i<playerJournal.size() ; i++ ){
            if ( playerJournal.size()!=0) {
                journal += playerJournal.get(i);
            }
        }
        return journal;
    }


	//SCORING ********************************************************************
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


    //ACTION ANIMATION *****************************************************************************
    public String GetPlayerLastTurnAction() {

        return playerLastTurnActions.size()>0?playerLastTurnActions.get(0):"Empty";
    }

    public String DeletePlayerLastTurnAction() {
        return playerLastTurnActions.remove(0);
    }

    public void SetPlayerLastTurnActions(String _action) {
        this.playerLastTurnActions.add(_action);
    }

}