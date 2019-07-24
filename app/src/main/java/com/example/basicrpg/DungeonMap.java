/*
* AUTHOR : CRANKYUNICORN
* LAST UPDATED : NEVER
*
*
*/

package com.example.basicrpg;

class DungeonMap {

    private static final DungeonMap DUNGEON_MAP = new DungeonMap();

    static DungeonMap GetDungeonMap() { return DUNGEON_MAP; }


    private Dungeon currentDungeon = null;

    public Dungeon GetCurrentDungeon() {return currentDungeon;}


    private void CreateNewDungeon(){
        currentDungeon = new Dungeon();
    }

    private void LoadDungeon(int slot) {
        //loads dungeon based on passed arguments
    }

    private DungeonMap() {
        //if argument present load from file if not create a map
        if(false){//load argument present
            //load
            LoadDungeon(0);
        }else{
            //create dungeon
            CreateNewDungeon();
        }
    }


}
