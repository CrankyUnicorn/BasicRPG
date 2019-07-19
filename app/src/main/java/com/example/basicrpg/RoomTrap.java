package com.example.basicrpg;

public class RoomTrap implements IRoomTrap {

    private String trapName;
    private boolean trapDisarmed;
	private boolean trapDetected;
    private int trapDamage;
	private int trapToDetect;
    private int trapToDisarm;
  

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
    RoomTrap(String _trapName, int _trapDamage, int _trapToDetect, int _trapToDisarm){
        trapName = _trapName;
        trapDisarmed = false;
		trapDetected = false;
        trapDamage = _trapDamage;
        trapToDetect = _trapToDetect;
        trapToDisarm = _trapToDisarm;
    }


    //TIGGERS
    @Override
    public boolean TriggerTrap() {
        //does logic when trap is activated
		if(trapDisarmed==false && trapDetected==false){
			
			trapDisarmed = true;
            trapDetected = true;
			return true;
		}
		
		return false;
    }
	
	@Override
    public boolean DetectTrap(int _detectSkill) {
        //does logic when Player Tries to Disarm trap
		if( trapDetected == false && trapToDetect < _detectSkill){
			
			trapDetected = true;
			return true;
		}
		
		return false;
    }
	
	@Override
    public boolean DisarmTrap(int _disarmSkill) {
        //does logic when Player Tries to Disarm trap
		if( trapDetected == true && trapDisarmed == false && trapToDisarm < _disarmSkill){
			
			trapDisarmed = true;
			return true;
		}
				
		return false;
    }
}
