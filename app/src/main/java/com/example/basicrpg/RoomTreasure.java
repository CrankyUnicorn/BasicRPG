package com.example.basicrpg;

import android.util.Log;


public class RoomTreasure implements IRoomTreasure {

    private boolean treasurePresent;

    private String treasureName;
    private String treasureDescription;

	private int treasureToDetect;
	private int treasureToUnlock;

    private boolean treasureVisible;
    private boolean treasureLocked;

    private Item treasureItem;

    RoomTreasure(boolean _treasurePresent ,String _treasureName, String _treasureDescription, int _treasureToDetect, boolean _treasureVisible, boolean _randomItem, Item _item){

        this.treasurePresent = _treasurePresent;
		this.treasureName = _treasureName;
		this.treasureDescription = _treasureDescription;
		this.treasureToDetect = _treasureToDetect;
		this.treasureVisible = _treasureVisible;
		if (_randomItem){
		    this.treasureItem = ItemsList.GetRandomItem();
		}else{
	        this.treasureItem = _item==null?ItemsList.GetItemFromList(0):ItemsList.CloneItem(_item);
		}
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
    public int TreasureToDetect(){
        return treasureToDetect;
    }

    @Override
    public int TreasureToUnlock(){
        return treasureToUnlock;
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
		  //does logic when Player Tries to Disarm trap
		if( treasureVisible == false && treasurePresent==true) {
            if (Player.GetCurrentPlayer().IsIlluminated()) {

                if (treasureToDetect < Player.GetCurrentPlayer().GetPlayerSanity() + Util.GenerateNumberBetween(1, 7)) {

                    treasureVisible = true;
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DETECT_TREASURE");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You detected a tresure");
                    return true;
                }
            }else{
                if (treasureToDetect < Player.GetCurrentPlayer().GetPlayerSanity() + Util.GenerateNumberBetween(1, 4)) {
					
					treasureVisible = true;
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DETECT_TREASURE");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You detected a tresure");
                    return true;
                }
            }
        }
		return false;
    }

    @Override
    public boolean OpenTreasure(){
        if(treasurePresent==true && treasureVisible==true && treasureItem!=null){
			
            Player.GetCurrentPlayer().AddToPlayerInventory(this.treasureItem);

            Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
            Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_TREASURE");
            Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

            Player.GetCurrentPlayer().SetPlayerJournal("You open the treasure box and found a " + this.treasureItem.GetItemName());
            this.treasureItem=null;

            return true;
			
		}else{
            Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
            Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_TREASURE");
            Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

            Player.GetCurrentPlayer().SetPlayerJournal("You open the treasure box and found a nothing");
            return false;
		}
	}

    @Override
    public Item GetItem() {
        return this.treasureItem;
    }
}
