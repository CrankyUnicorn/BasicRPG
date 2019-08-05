package com.example.basicrpg;

import android.util.Log;


public class RoomTreasure implements IRoomTreasure {

    private boolean treasurePresent;

    private String treasureName;
    private String treasureDescription;

    private boolean treasureVisible;
    private boolean treasureLocked;

    RoomTreasure(String _treasureName, String _treasureDescription){
	
		this.treasureName = _treasureName;
		this.treasureDescription = _treasureDescription;
	
	}

    @Override
    public boolean TreasurePresent() {
        return treasurePresent;
    }

    @Override
    public String TreasureName() {
        return treasureName;
    }

    @Override
    public String TreasureDescription() {
        return treasureDescription;
    }

    @Override
    public boolean TreasureVisible() {
        return treasureVisible;
    }

    @Override
    public boolean TreasureLocked() {
        return treasureLocked;
    }

    @Override
    public boolean FindTreasure() {
        return false;
    }

    @Override
    public boolean OpenTreasure() {
        return false;
    }

    @Override
    public Item GetItem() {
        return null;
    }
}
