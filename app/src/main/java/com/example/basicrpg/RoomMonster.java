package com.example.basicrpg;

import android.util.Log;

import com.example.basicrpg.IRoomMonster;


public class RoomMonster implements IRoomMonster {

    private String monsterName;
    private String monsterDescription;
	
    private boolean monsterDead;
	
    private int monsterDamage;
    private int monsterHealth;
    private int monsterArmor;
    private int monsterHitDodge;
	
	

	RoomMonster(String _monsterName, String _monsterDescription){
	
		this.monsterName = _monsterName;
		this.monsterDescription = _monsterDescription;
	
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


		@Override
    public int MonsterAttacks() {
        return monsterDamage + Util.GenerateNumberBetween(0,6) + 1;
	}
	
	
		@Override
    public boolean MonsterDefends(int opponentHitDodge, int opponentAttack ) {
		if(opponentHitDodge + Util.GenerateNumberBetween(0,6) + 1 < monsterHitDodge + Util.GenerateNumberBetween(0,6) + 1){
			
			monsterHealth =monsterHealth-(opponentAttack-monsterArmor);
			if(monsterHealth < 0){
				monsterHealth = 0; 
				monsterDead=true;
				}
			monsterArmor--;
			if(monsterArmor<0){
				monsterArmor=0;
				}
			
			return false;
		}
		
        return true; //if not hitted
	}

}
