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

		return monsterPresent ? monsterDead ? 0 :ImageReferences.imageFoe[Util.GenerateNumberBetween(0,ImageReferences.imageFoe.length)]: 0;

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
			
			monsterHealth =monsterHealth-(opponentAttack - monsterArmor);
			if(monsterHealth < 0){
				monsterHealth = 0; 
				monsterDead = true;
			}
			monsterArmor--;
			if(monsterArmor<0){
				monsterArmor = 0;
			}
			
			return false;
		}
		
        return true; //if not hitted
	}


	@Override
	public void FightFoe(){

		if(monsterPresent == true){
			if(monsterDead == false){
				
					Player.GetCurrentPlayer().SetPlayerHealth(-5);
					Player.GetCurrentPlayer().PlayerDefends(MonsterHitDodge(),MonsterAttacks());
					MonsterDefends(Player.GetCurrentPlayer().GetPlayerHitDodge(),Player.GetCurrentPlayer().PlayerAttack());
				
			}
		}
	}

}
