/*
* BASIC RPG - DUNGEON CRAWLER
* CRANKYUNICORN 2019
* azedo.peter@gmail.com
*/

package com.example.basicrpg;

public class RoomTrap implements IRoomTrap {

    private boolean trapPresent;
    private String trapName;
    private boolean trapDisarmed;
	private boolean trapDetected;
    private int trapDamage;
	private int trapToDetect;
    private int trapToDisarm;


    @Override
    public boolean TrapPresent() {
        return trapPresent;
    }


    @Override
    public String TrapName() {
        return trapName;
    }

    @Override
    public boolean TrapDisarmed() {
        return trapDisarmed;
    }

    @Override
    public boolean TrapDetected() {
        return trapDetected;
	}

    @Override
    public int TrapDamage() {
        return trapDamage;
    }

	@Override
    public int TrapToDetect() {
        return trapToDetect;
    }

    @Override
    public int TrapToDisarm() {
        return trapToDisarm;
    }

  

    //CONSTRUCTOR OVERLOAD
    RoomTrap(boolean _trapPresent,String _trapName, int _trapDamage, int _trapToDetect, int _trapToDisarm){
        trapPresent = _trapPresent;
        trapName = _trapName;
        trapDisarmed = false;
		trapDetected = false;
        trapDamage = _trapDamage;
        trapToDetect = _trapToDetect;
        trapToDisarm = _trapToDisarm;
    }

    RoomTrap(){}

    //TIGGERS
    @Override
    public boolean TriggerTrap() {
        //does logic when trap is activated
		if(trapDisarmed==false && trapPresent==true && trapDetected == false ){

            if (Player.GetCurrentPlayer().IsIlluminated()){

                if(Player.GetCurrentPlayer().GetPlayerHitDodge()+Util.GenerateNumberBetween(1,7)>trapToDetect+trapToDisarm){

                   Player.GetCurrentPlayer().SetPlayerHealth(-trapDamage);
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");


                    Player.GetCurrentPlayer().SetPlayerJournal("You tripped a " + trapName + " and got damaged " + trapDamage + " points");

                    trapDisarmed = true;
                    trapDetected = true;
                    return true;

                }else{
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DISARM_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");


                    Player.GetCurrentPlayer().SetPlayerJournal("You tripped a " + trapName + " but you dodged in time");

                    trapDisarmed = true;
                    trapDetected = true;
                    return true;
                }
            }else{
                if(Player.GetCurrentPlayer().GetPlayerHitDodge()+Util.GenerateNumberBetween(1,4)>trapToDetect+trapToDisarm){

                    Player.GetCurrentPlayer().SetPlayerHealth(-trapDamage);
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You tripped a " + trapName + " and got damaged " + trapDamage + " points");

                    trapDisarmed = true;
                    trapDetected = true;
                    return true;
                }else{
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DISARM_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You tripped a " + trapName + " but you dodged in time");

                    trapDisarmed = true;
                    trapDetected = true;
                    return true;
                }
            }
		}
		
		return false;
    }
	
	@Override
    public boolean DetectTrap() {
        //does logic when Player Tries to Disarm trap
		if( trapDetected == false && trapPresent==true) {
            if (Player.GetCurrentPlayer().IsIlluminated()) {

                if (trapToDetect < Player.GetCurrentPlayer().GetPlayerSanity() + Util.GenerateNumberBetween(1, 7)) {
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DETECT_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You detected a trap");

                    trapDetected = true;
                    return true ;
                }
            }else{
                if (trapToDetect < Player.GetCurrentPlayer().GetPlayerSanity() + Util.GenerateNumberBetween(1, 4)) {
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DETECT_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You detected a trap in the darkness");

                    trapDetected = true;
                    return true;
                }
            }
        }
		return false;
    }
	
	@Override
    public boolean DisarmTrap() {
        //does logic when Player Tries to Disarm trap

        if( trapDetected == true && trapPresent==true) {
            if (Player.GetCurrentPlayer().IsIlluminated()) {

                if (trapToDetect < Player.GetCurrentPlayer().GetPlayerSanity() + Util.GenerateNumberBetween(1, 7)) {
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DISARM_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You disarmed a trap");

                    trapDisarmed = true;
                    return false;
                }
            }else{
                if (trapToDetect < Player.GetCurrentPlayer().GetPlayerSanity() + Util.GenerateNumberBetween(1, 4)) {
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("PLAYER_DISARM_TRAP");
                    Player.GetCurrentPlayer().SetPlayerLastTurnActions("NEUTRAL");

                    Player.GetCurrentPlayer().SetPlayerJournal("You disamerd a trap in the darkness");

                    trapDisarmed = true;
                    return false;
                }
            }
        }

        return true;
    }
}
