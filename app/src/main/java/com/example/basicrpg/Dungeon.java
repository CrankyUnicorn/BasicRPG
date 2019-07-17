package com.example.basicrpg;

public class Dungeon {


    private String dungeonName;

    private int dungeonTotalLevels;
    private int dungeonTotalSections;
    private int dungeonTotalRooms;

    private int dungeonTotalLevelsExplored;
    private int dungeonTotalSectionsExplored;
    private int dungeonTotalRoomsExplored;

    private List<DungeonLevel> listDungeonLevels = new ArrayList<DungeonLevel>();


    //CONSTRUCTOR
    public void Dungeon(String _dungeonName,){

        dungeonName = _dungeonName;

        Populator();
    }

    public void Dungeon(){

        Populator();
    }

    //POPULATOR
    private void Populator(){

        dungeonTotalLevels = 0;
        dungeonTotalSections = 0;
        dungeonTotalRooms = 0;

        dungeonTotalLevelsExplored = 0;
        dungeonTotalSectionsExplored = 0;
        dungeonTotalRoomsExplored = 0;

    }

    //ACESSORS

    //NAME ACESSOR
    public void SetDungeonName(String _dungeonName){

        if(dungeonName==null){

            dungeonName = _dungeonName;
        }

    }

    public String GetDungeonName(){

        return dungeonName;
    }


    //DungeonTotalLevels ACESSOR
    public void SetDungeonTotalLevels(String _dungeonTotalLevels){

        if(dungeonTotalLevels==0 || dungeonTotalLevels==null){

            dungeonTotalLevels = _dungeonTotalLevels;
        }

    }

    public int GetDungeonTotalLevels(){

        //maybe usefull to recalculate all the levels on demand
        return dungeonTotalLevels;
    }


    //DungeonTotalSections ACESSOR
    public void SetDungeonTotalSections(String _dungeonTotalSections){

        if(dungeonTotalSections == 0 || dungeonTotalSections == null){

            dungeonTotalSections = _dungeonTotalSections;
        }
    }

    public int GetDungeonTotalSections(){

        //maybe usefull to recalculate all the sections on demand
        return dungeonTotalSections;
    }


    //DungeonTotalRooms ACESSOR
    public void SetDungeonTotalRooms(String _dungeonTotalRooms){

        if(dungeonTotalRooms == 0 || dungeonTotalRooms == null){

            dungeonTotalRooms = _dungeonTotalRooms;
        }

    }

    public int GetDungeonTotalRooms(){

        //maybe usefull to recalculate all the rooms on demand
        return dungeonTotalRooms;
    }


    //DungeonTotalLevelsExplored ACESSOR
    public void SetDungeonTotalLevelsExplored(String _dungeonTotalLevelsExplored){

        dungeonTotalLevelsExplored = _dungeonTotalLevelsExplored;
    }

    public int GetDungeonTotalLevelsExplored(){

        return dungeonTotalLevelsExplored;
    }


    //DungeonTotalSectionsExplored ACESSOR
    public void SetDungeonTotalSectionsExplored(String _dungeonTotalSectionsExplored){

        dungeonTotalSectionsExplored = _dungeonTotalSectionsExplored;
    }

    public int GetDungeonTotalSectionsExplored(){

        return dungeonTotalSectionsExplored;
    }


    //DungeonTotalRoomsExplored ACESSOR
    public void SetDungeonTotalRoomsExplored(String _dungeonTotalRoomsExplored){

        dungeonTotalRoomsExplored = _dungeonTotalRoomsExplored;
    }

    public int GetDungeonTotalRoomsExplored(){

        return dungeonTotalRoomsExplored;
    }

}