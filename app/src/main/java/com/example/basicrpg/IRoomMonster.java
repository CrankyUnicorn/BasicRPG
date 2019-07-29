package com.example.basicrpg;

public interface IRoomMonster {

    public boolean MonsterPresent();

    public String MonsterName();
    public String MonsterDescription();

    public boolean MonsterDead();

    public int MonsterDamage();
    public int MonsterHealth();
    public int MonsterArmor();
    public int MonsterHitDodge();

    public int MonsterAttacks();
	public boolean MonsterDefends(int opponentHitDodge, int opponentAttack );
	
}
