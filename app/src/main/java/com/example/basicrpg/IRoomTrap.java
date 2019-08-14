package com.example.basicrpg;

public interface IRoomTrap {

    public boolean TrapPresent();

    public String TrapName();

    public boolean TrapDisarmed();
	public boolean TrapDetected();

    public int TrapDamage();
    public int TrapToDetect();
    public int TrapToDisarm();

    public boolean TriggerTrap();
	public boolean DetectTrap();
	public boolean DisarmTrap();
}
