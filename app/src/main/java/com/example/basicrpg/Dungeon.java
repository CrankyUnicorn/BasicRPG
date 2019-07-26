package com.example.basicrpg;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Dungeon {


    private String dungeonName;

    private String dungeonCreation;

    private int dungeonTotalLevels;
    private int dungeonTotalSections;
    private int dungeonTotalRooms;

    private int dungeonTotalLevelsExplored;
    private int dungeonTotalSectionsExplored;
    private int dungeonTotalRoomsExplored;

    private List<DungeonLevel> childLevels = new ArrayList<DungeonLevel>();

    //private List<DungeonLevel> listDungeonLevels = new ArrayList<DungeonLevel>();
    //private List<DungeonSection> listDungeonSections = new ArrayList<DungeonSection>();
    private List<DungeonRoom> listDungeonRooms = new ArrayList<DungeonRoom>();


    //CONSTRUCTOR
    Dungeon() {
        RoomsList.getInstance();
        VerifyDungeonList();

        CreateDungeonLevels();

        MapDungeon();

        ConnectRooms();

        Log.d("Initialization","Complete!");
    }


    //VERIFIER
    private void VerifyDungeonList() {

        dungeonName = GameSettings.DUNGEON_NAME.isEmpty() ? DungeonNameGenerator.GenerateDungeonName() : GameSettings.DUNGEON_NAME;

        dungeonTotalLevels = GameSettings.DUNGEON_LEVELS < 1 ? 1 : GameSettings.DUNGEON_LEVELS;

        dungeonTotalSections = GameSettings.DUNGEON_SECTIONS < dungeonTotalLevels ? dungeonTotalLevels : GameSettings.DUNGEON_SECTIONS;

        dungeonTotalRooms = GameSettings.DUNGEON_ROOMS < dungeonTotalSections ? dungeonTotalSections : GameSettings.DUNGEON_ROOMS;

    }


    //CREATE DUNGEON STRUCTURE
    private void CreateDungeonLevels() {

        int sectionsRemainder = dungeonTotalSections;
        int roomRemainder = dungeonTotalRooms;


        for (int i = dungeonTotalLevels - 1; i >= 0; i--) {

            Log.d("DUNGEON", "Creates Level!");

            int levelSections;
            int levelRooms;


            if (i > 0) {

                levelSections = Util.GenerateNumberBetween(1, (sectionsRemainder) - i);
                levelRooms = Util.GenerateNumberBetween(levelSections, roomRemainder - (sectionsRemainder - levelSections));

            } else {

                levelSections = sectionsRemainder;
                levelRooms = roomRemainder;
            }

            sectionsRemainder -= levelSections;
            roomRemainder -= levelRooms;


            Log.d("levelSectors", String.valueOf(levelSections));
            Log.d("levelRooms", String.valueOf(levelRooms));
            //Log.d("sectorRemainder", String.valueOf(sectorRemainder));
            //Log.d("roomRemainder", String.valueOf(roomRemainder));


            childLevels.add(new DungeonLevel(levelSections, levelRooms));
        }

    }


    //MAP DUNGEON TREE AND CREATE REFERENCE LIST OF ALL ROOMS
    private void MapDungeon() {

		int qLevels = 0; 
		int qSections = 0; 
		int qRooms = 0; 
		
        listDungeonRooms.clear();

        for (DungeonLevel n : childLevels) {
			qLevels++;
            for (DungeonSection m : n.GetChildSections()) {
                qSections++;
				for (DungeonRoom l : m.GetChildRooms()) {
        
					listDungeonRooms.add(l);

					qRooms++;
                }
            }
        }


		//Log.d("DungeonLevels",String.valueOf(qLevels));
		//Log.d("DungeonSections",String.valueOf(qSections));
		//Log.d("DungeonRooms",String.valueOf(qRooms));
		
		Log.d("ROOMSLIST SIZE",String.valueOf(listDungeonRooms.size()));

    }



    //CONNECT ROOMS DOORS TO EACH OTHER
    private void ConnectRooms() {
        for (int n = 0; n < childLevels.size(); n++) {


            for (int m = 0; m < childLevels.get(n).GetChildSections().size(); m++) {


                DungeonSection _selectedSection = childLevels.get(n).GetChildSections().get(m);


                int previousID = 0;//finds previous section |level
                if (n == 0) {
                    if (m == 0) {

                        previousID = childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(0).RoomID();
                    }else if (m > 0) {

                        previousID = childLevels.get(n).GetChildSections().get(m - 1).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m - 1).GetChildRooms().size() - 1).RoomID();
                    }
                } else if (n > 0) {
                    if (m == 0) {

                        previousID = childLevels.get(n - 1).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n - 1).GetChildSections().get(m).GetChildRooms().size() - 1).RoomID();
                    }
                }

                int nextID = 0;//find next section level
                if (n == childLevels.size() - 1) {
                    if (m == childLevels.get(n).GetChildSections().size() - 1) {

                        nextID = childLevels.get(0).GetChildSections().get(0).GetChildRooms().get(0).RoomID();

                    }else if (m < childLevels.get(n).GetChildSections().size() - 1) {

                        nextID = childLevels.get(n).GetChildSections().get(m + 1).GetChildRooms().get(0).RoomID();
                    }
                } else if (n < childLevels.size() - 1) {
                    if (m == childLevels.get(n).GetChildSections().size() - 1) {


                        nextID = childLevels.get(n + 1).GetChildSections().get(0).GetChildRooms().get(0).RoomID();
                    }
                }


                if (_selectedSection.GetChildRooms().size() == 1) {//if only haves one room

                    //point only to back exit
                    _selectedSection.GetChildRooms().get(0).SetRoomExitsId(previousID, 3);//sector exit ||level exit||or exit win

                    _selectedSection.GetChildRooms().get(0).SetRoomExitsNames("Go To " + String.valueOf(previousID), 3);

                    //point only to back exit
                    _selectedSection.GetChildRooms().get(0).SetRoomExitsId(nextID, 0);//sector exit ||level exit||or exit win

                    _selectedSection.GetChildRooms().get(0).SetRoomExitsNames("Go To " + String.valueOf(nextID), 0);


                } else {

                    //point only to back exit
                    _selectedSection.GetChildRooms().get(0).SetRoomExitsId(previousID, 3);//sector exit ||level exit||or exit win

                    _selectedSection.GetChildRooms().get(0).SetRoomExitsNames("Go To " + String.valueOf(previousID), 3);


                    //point only to front exit
                    _selectedSection.GetChildRooms().get(_selectedSection.GetChildRooms().size() - 1).SetRoomExitsId(nextID, 0);//sector exit ||level exit||or exit win

                    _selectedSection.GetChildRooms().get(_selectedSection.GetChildRooms().size() - 1).SetRoomExitsNames("Go To " + String.valueOf(nextID), 0);



                    for (int l = 0; l < _selectedSection.GetChildRooms().size(); l++) {


                        int exitDoorsNumber = 0;

                        int connections  = Util.GenerateNumberBetween(0, 10);

                        if(connections<1){exitDoorsNumber=0;}
                        else if(connections<3){exitDoorsNumber=1;}
                        else if(connections<6){exitDoorsNumber=2;}
                        else if(connections<8){exitDoorsNumber=3;}
                        else{exitDoorsNumber=4;}

                        Log.d("make exits x->",String.valueOf(exitDoorsNumber));



                        int failedKTimes=0;

                        for (int k = 0; k < exitDoorsNumber; k++) {

                            Log.d("creating exits",String.valueOf(k)+"|"+String.valueOf(exitDoorsNumber));

                            int formula = l + k + 1 - failedKTimes;

                            for (int i = 0; i < 4 ; i++) {//ON THIS ROOM

                                if (formula < _selectedSection.GetChildRooms().size()) {
                                    //DELETE DOOR REF OF TARGET ON THIS ROOM DOOR TO BE REWRITEN
                                    if(_selectedSection.GetChildRooms().get(formula).RoomID() == _selectedSection.GetChildRooms().get(l).GetRoomExitIdByIndex(i)) {

                                        //cleans possible references to write only one next for loop
                                        _selectedSection.GetChildRooms().get(l).SetRoomExitsId(0, i);
                                        _selectedSection.GetChildRooms().get(l).SetRoomExitsNames("", i);

                                    }
                                }

                                //DELETE DOOR REF IF EQUAL OF THIS SAME ROOM
                                if(_selectedSection.GetChildRooms().get(l).RoomID() == _selectedSection.GetChildRooms().get(l).GetRoomExitIdByIndex(i)) {

                                    //cleans possible references to write only one next for loop
                                    _selectedSection.GetChildRooms().get(l).SetRoomExitsId(0, i);
                                    _selectedSection.GetChildRooms().get(l).SetRoomExitsNames("", i);

                                }
                            }


                            if (_selectedSection.GetChildRooms().get(l).GetRoomExitIdByIndex(k) == 0) {

                                Log.d("creating exits","empty exit available");



                                if (formula < _selectedSection.GetChildRooms().size()) {

                                    Log.d("creating exits","enough room forward");




                                    //SETS DOOR K of this room to the FORMULA.ROOMID target room
                                    _selectedSection.GetChildRooms().get(l).SetRoomExitsId(_selectedSection.GetChildRooms().get(formula).RoomID(), k);

                                    //SETS DOOR K of this room to the FORMULA.ROOMNAME target room
                                   // _selectedSection.GetChildRooms().get(l).SetRoomExitsNames(_selectedSection.GetChildRooms().get(formula).RoomName(), k);

                                    //TESTING TOOL
                                    _selectedSection.GetChildRooms().get(l).SetRoomExitsNames("Go To "+String.valueOf(_selectedSection.GetChildRooms().get(formula).RoomID()), k);


                                    for (int i = 0; i < 4 ; i++) {//ON TARGET ROOM
                                        //DELETES REF ON DOOR OF TARGET OF THE ACTUAL ROOM
                                        if (_selectedSection.GetChildRooms().get(formula).GetRoomExitIdByIndex(i) == _selectedSection.GetChildRooms().get(l).GetRoomExitIdByIndex(k)) {

                                            //cleans possible references to write only one next for loop
                                            _selectedSection.GetChildRooms().get(formula).SetRoomExitsId(0, i);
                                            _selectedSection.GetChildRooms().get(formula).SetRoomExitsNames("", i);

                                        }

                                    }

                                    for (int i = 0; i < 4 ; i++) {//ON TARGET ROOM{
                                        //SETS REF OF ACTUAL ROOM AT TARGET ROOM
                                        if(_selectedSection.GetChildRooms().get(formula).GetRoomExitIdByIndex(i) == 0 ) {

                                            //this sets the door 0 ate the targeted room to the one that target it
                                            _selectedSection.GetChildRooms().get(formula).SetRoomExitsId(_selectedSection.GetChildRooms().get(l).RoomID(), i);
                                            //  _selectedSection.GetChildRooms().get(formula).SetRoomExitsNames(_selectedSection.GetChildRooms().get(l).RoomName(), i);
                                            _selectedSection.GetChildRooms().get(formula).SetRoomExitsNames("Go To " + String.valueOf(_selectedSection.GetChildRooms().get(l).RoomID()), i);
                                            break;
                                        }
                                    }
                                }
                            }else{
                                failedKTimes++;
                            }

                        }
                    }

                }

            }
        }

    }


    //ACESSORS
    //CHILD LIST ACESSOR
    public List<DungeonLevel> GetChildLevels() {
        return childLevels;
    }

    //ROOM LIST ACESSOR
    public void SetDungeonRooms(DungeonRoom _room) {
        listDungeonRooms.add(_room);
    }

    public List<DungeonRoom> GetDungeonRooms() {
        return listDungeonRooms;
    }


    //NAME ACESSOR
    public void SetDungeonName(String _dungeonName) {

        if (dungeonName.isEmpty()) {

            dungeonName = _dungeonName;
        }

    }

    public String GetDungeonName() {
        return dungeonName;
    }

    //Creation ACESSOR
    public void SetDungeonCreation() {

        if (dungeonCreation.isEmpty()) {
            Date nowDate = new Date();
            DateFormat thisDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            dungeonCreation = thisDateFormat.format(nowDate);
        }

    }

    public String GetDungeonCreation() {
        return dungeonCreation;
    }

    //EXPLORED
    public int GetDungeonTotalLevelsExplored() {
        return dungeonTotalLevelsExplored;
    }

    public int GetDungeonTotalSectionsExplored() {
        return dungeonTotalSectionsExplored;
    }

    public int GetDungeonTotalRoomsExplored() {
        return dungeonTotalRoomsExplored;
    }


}