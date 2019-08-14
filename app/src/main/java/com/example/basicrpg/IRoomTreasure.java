package com.example.basicrpg;

public interface IRoomTreasure {

    public boolean TreasurePresent();

    public String TreasureName();
    public String TreasureDescription();

    public int TreasureToDetect();
    public int TreasureToUnlock();

    public boolean TreasureVisible();
    public boolean TreasureLocked();


    public boolean FindTreasure();
    public boolean OpenTreasure();
	
    public Item GetItem();
	
}