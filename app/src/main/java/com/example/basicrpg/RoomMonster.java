/*
* BASIC RPG - DUNGEON CRAWLER
* CRANKYUNICORN 2019
* azedo.peter@gmail.com
*/

package com.example.basicrpg;

import android.util.Log;

import com.example.basicrpg.IRoomMonster;


public class RoomMonster implements IRoomMonster {


	private boolean monsterPresent;

    private String monsterName;
    private String monsterDescription;

    private boolean monsterDead;

    private int monsterImage;
	
    private int monsterDamage;
    private int monsterHealth;
    private int monsterArmor;
    private int monsterHitDodge;
	
	

	RoomMonster(boolean _monsterPresent, String _monsterName, String _monsterDescription,boolean _monsterDead,int _monsterImage, int _monsterDamage, int _monsterHealth,int _monsterArmor,int _monsterHitDodge){

		this.monsterPresent = _monsterPresent;
		this.monsterName = _monsterName;
		this.monsterDescription = _monsterDescription;
		this.monsterDead = _monsterDead;
		this.monsterImage = _monsterImage;
		this.monsterDamage = _monsterDamage;
		this.monsterHealth = _monsterHealth;
		this.monsterArmor = _monsterArmor;
		this.monsterHitDodge = _monsterHitDodge;
	
	}

	@Override
	public boolean MonsterPresent() {
		return monsterPresent;
	}

	@Override
    public String MonsterName() {
        return monsterName;
    }


	@Override
    public String MonsterDescription() {
        return monsterDescription;
    }


	@Override
    public boolean MonsterDead() {
        return monsterDead;
	}

	@Override
	public int MonsterImage(){

		return monsterPresent ? monsterDead ? 0 :monsterImage: 0;

	}

	@Override
    public int MonsterDamage() {
        return monsterDamage;
	}

	@Override
    public int MonsterHealth() {
        return monsterHealth;
	}
	
		@Override
    public int MonsterArmor() {
        return monsterArmor;
	}
	
		@Override
    public int MonsterHitDodge() {
        return monsterHitDodge;
	}

	
	//####FIGHT#####
	@Override
    public int MonsterAttacks() {
		
        return monsterDamage + Util.GenerateNumberBetween(0,6) + 1;
	}
	
	
	@Override
    public boolean MonsterDefends(int opponentHitDodge, int opponentAttack ) {
		if(opponentHitDodge + Util.GenerateNumberBetween(0,6) + 1 > monsterHitDodge + Util.GenerateNumberBetween(0,6) + 1){
			
			//print to history 
			int damage = opponentAttack - monsterArmor;
				damage = damage<0?0:damage;
			
			monsterHealth -= damage;

			for (int i=0; i<damage;i++) {
				//Log.d("ANIMATION","Monster Hit");
				Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
				Player.GetCurrentPlayer().SetPlayerLastTurnActions("FOE_HIT");
				Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
			}

			String damageDescription ="";
			switch(damage){
				
				case 0 : damageDescription = "but caused no damage"; break;
				case 1 : damageDescription = "causing a scratch"; break;
				case 2 : damageDescription = "causing a minor injury"; break;
				case 3 : damageDescription = "causing a injury"; break;
				case 4 : damageDescription = "causing a gapping wound"; break;
				default : damageDescription = "causing a brutal bleeding"; break;
				
			}
			Player.GetCurrentPlayer().SetPlayerJournal("You hit the foe " + damageDescription);
			
			if(monsterHealth <= 0){
				monsterHealth = 0; 
				monsterDead = true;
				Player.GetCurrentPlayer().SetPlayerJournal("You slained the foe");
			}
			
			
			
			if(monsterArmor>0){
				monsterArmor--;
				if(monsterArmor<=0){
					//Animation
					Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
					Player.GetCurrentPlayer().SetPlayerLastTurnActions("FOE_ARMOR_BREAK");
					Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

					Player.GetCurrentPlayer().SetPlayerJournal("You broke the foe s armor");
				}
			}else{
				monsterArmor = 0;
			}
			
			return false;
		}

		//Animation
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("FOE_DODGE");
		Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");


		Player.GetCurrentPlayer().SetPlayerJournal("You did not hit the foe");
        return true; //if not hitted
	}


	@Override
	public void FightFoe(){

		if(monsterPresent == true){
			if(monsterDead == false){
				
					Player.GetCurrentPlayer().SetPlayerJournal("You fought a foe");
					Player.GetCurrentPlayer().PlayerDefends(MonsterHitDodge(),MonsterAttacks());
					MonsterDefends(Player.GetCurrentPlayer().GetPlayerHitDodge(),Player.GetCurrentPlayer().PlayerAttack());
				
			}
		}
	}
	
	@Override
	public boolean FleeFoe(){

		if(monsterPresent == true){
			if(monsterDead == false){
				
				if(Player.GetCurrentPlayer().IsIlluminated()==true){
					if(Util.GenerateNumberBetween(0,6)<4){
						
						Player.GetCurrentPlayer().SetPlayerJournal("You tried to flee a foe but you failed");
						
						Player.GetCurrentPlayer().PlayerDefends(MonsterHitDodge(),MonsterAttacks());
						return false;					
					}
					Player.GetCurrentPlayer().SetPlayerJournal("You here successful fleeing a foe");
				}else{
					if(Util.GenerateNumberBetween(0,6)<2){
						
						Player.GetCurrentPlayer().SetPlayerJournal("You tried to flee a foe in darkness but you failed");
						
						Player.GetCurrentPlayer().PlayerDefends(MonsterHitDodge(),MonsterAttacks());
						return false;
					}
					Player.GetCurrentPlayer().SetPlayerJournal("You here successful fleeing a foe in darkness");
				}
			}
		}
		
		return true;
	}

}
