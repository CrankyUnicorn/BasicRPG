/*
* BASIC RPG - DUNGEON CRAWLER
* CRANKYUNICORN 2019
* azedo.peter@gmail.com
*/

package com.example.basicrpg;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

        AddEntranceAndExitRooms();

        MapDungeon();

        ConnectRooms();

        Log.d("*****","*****");
        Log.d("Initialization","Complete!");

        //TESTING TOOLS
        MapDungeonDoors();//TESTING...
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

    //CREATE ENTRANCE AND EXIT ROOM OF DUNGEON BY SUBSTITUTION
    private void AddEntranceAndExitRooms(){
        childLevels.get(0).GetChildSections().get(0).GetChildRooms().set(0, RoomsList.getInstance().GetEntranceRoom());

        childLevels.get(childLevels.size()-1)
                .GetChildSections().get(childLevels.get(childLevels.size()-1).GetChildSections().size()-1)
                .GetChildRooms().set(
                        childLevels.get(childLevels.size()-1).GetChildSections().get(childLevels.get(childLevels.size()-1).GetChildSections().size()-1).GetChildRooms().size()-1,
                        RoomsList.getInstance().GetExitRoom());


    }


    //MAP DUNGEON AND CREATE REFERENCE LIST OF ALL ROOMS
    private void MapDungeon() {

		int qLevels = 0;
		int qSections = 0;
		int qRooms = 0; 
		
        listDungeonRooms.clear();
        for (int n = 0; n < childLevels.size(); n++) {


            for (int m = 0; m < childLevels.get(n).GetChildSections().size(); m++) {


                for (int l = 0; l < childLevels.get(n).GetChildSections().get(m).GetChildRooms().size(); l++) {

                    childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(l).RemapRoomId();

                    if(n== 0 && m == 0 && l == 0){

                    }else if( n == childLevels.size()-1 && m == childLevels.get(n).GetChildSections().size()-1 && l ==  childLevels.get(n).GetChildSections().get(m).GetChildRooms().size()-1){

                    }else{
                        childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(l).RemapRoomImage(n);

                    }


					listDungeonRooms.add( childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(l));

					qRooms++;
                }

                qSections++;
            }
            qLevels++;
        }


		//Log.d("DungeonLevels",String.valueOf(qLevels));
		//Log.d("DungeonSections",String.valueOf(qSections));
		//Log.d("DungeonRooms",String.valueOf(qRooms));




		Log.d("ROOMSLIST SIZE",String.valueOf(listDungeonRooms.size()));

    }



    //CONNECT ROOMS DOORS TO EACH OTHER *********************************************************
    private void OldConnectRooms() {

        //first cycle
        for (int n = 0; n < childLevels.size(); n++) {

            for (int m = 0; m < childLevels.get(n).GetChildSections().size(); m++) {


                DungeonSection _selectedSection = childLevels.get(n).GetChildSections().get(m);
                DungeonRoom referedRoom = new DungeonRoom();


                //SELECT ROOM
                for (int l = 0; l < _selectedSection.GetChildRooms().size(); l++) {


                    //Log.d("creating exits",String.valueOf(k)+"|"+String.valueOf(exitDoorsNumber-1));

                    //int formula = l + i + 1;

                    for (int i = 0; i < 4 ; i++) {//ON THIS ROOM

                        int formula = l + i + 1;
                        if (formula < _selectedSection.GetChildRooms().size()) {
                            //DELETE ALL DOOR REFS OF TARGET ON THIS ROOM DOORS TO BE REWRITEN ONLY ONCE
                            if(_selectedSection.GetChildRooms().get(formula).RoomID() == _selectedSection.GetChildRooms().get(l).GetRoomExitIdByIndex(i)) {

                                //cleans possible references to write only one next for loop
                                _selectedSection.GetChildRooms().get(l).SetRoomExitsId(0, i);
                                _selectedSection.GetChildRooms().get(l).SetRoomExitsNames("", i);

                            }
                        }

                        //DELETE ALL DOOR REFS IF EQUAL OF THIS SAME ROOM
                        if(_selectedSection.GetChildRooms().get(l).RoomID() == _selectedSection.GetChildRooms().get(l).GetRoomExitIdByIndex(i)) {

                            //cleans possible references to write only one next for loop
                            _selectedSection.GetChildRooms().get(l).SetRoomExitsId(0, i);
                            _selectedSection.GetChildRooms().get(l).SetRoomExitsNames("", i);

                        }
                    }

                    for (int j = 0; j < 4 ; j++) {//ON THIS ROOM

                        int formula = l + j + 1;


                        if (formula < _selectedSection.GetChildRooms().size()) {

                            //Log.d("creating exits","enough room forward");

                            if(_selectedSection.GetChildRooms().get(l).GetRoomExitIdByIndex(j) == 0){

                                if(Util.GenerateNumberBetween(0,4)>j){

                                    //SETS DOOR K of this room to the FORMULA.ROOMID target room
                                    _selectedSection.GetChildRooms().get(l).SetRoomExitsId(_selectedSection.GetChildRooms().get(formula).RoomID(), j);

                                    //SETS DOOR K of this room on the FORMULA.ROOMNAME target room
                                    _selectedSection.GetChildRooms().get(l).SetRoomExitsNames("Go To "+String.valueOf(_selectedSection.GetChildRooms().get(formula).RoomName()), j);
                                    // _selectedSection.GetChildRooms().get(l).SetRoomExitsNames("Go To "+String.valueOf(_selectedSection.GetChildRooms().get(formula).RoomID()), k);

                                }

                                for (int i = 0; i < 4 ; i++) {//ON TARGET ROOM
                                    //DELETES REF ON DOOR ON TARGET OF THE ACTUAL ROOM
                                    if (_selectedSection.GetChildRooms().get(formula).GetRoomExitIdByIndex(i) == _selectedSection.GetChildRooms().get(l).RoomID()) {

                                        //cleans possible references to write only one next for loop
                                        _selectedSection.GetChildRooms().get(formula).SetRoomExitsId(0, i);
                                        _selectedSection.GetChildRooms().get(formula).SetRoomExitsNames("", i);

                                    }

                                }

                                    for (int i = 0; i < 4 ; i++) {//ON TARGET ROOM
                                        if(Util.GenerateNumberBetween(0,8)>i){
                                        //SETS REF OF ACTUAL ROOM AT TARGET ROOM
                                        if(_selectedSection.GetChildRooms().get(formula).GetRoomExitIdByIndex(i) == 0 ) {

                                            //this sets the door 0 at the targeted room to the one that target it
                                            _selectedSection.GetChildRooms().get(formula).SetRoomExitsId(_selectedSection.GetChildRooms().get(l).RoomID(), i);
                                            _selectedSection.GetChildRooms().get(formula).SetRoomExitsNames("Go To " + String.valueOf(_selectedSection.GetChildRooms().get(l).RoomName()), i);
                                            // _selectedSection.GetChildRooms().get(formula).SetRoomExitsNames("Go To " + String.valueOf(_selectedSection.GetChildRooms().get(l).RoomID()), i);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                if(m == 0 && n == 0){

                    if( childLevels.get(n).GetChildSections().get(m).GetChildRooms().size()==1) {
                        if(childLevels.get(n).GetChildSections().size()>1){
                            referedRoom = childLevels.get(n).GetChildSections().get(m+1).GetChildRooms().get(0);
                        }else{
                            referedRoom = childLevels.get(n+1).GetChildSections().get(0).GetChildRooms().get(0);
                        }
                    }else{
                        referedRoom = childLevels.get(n).GetChildSections().get(0).GetChildRooms().get(1);
                    }


                    childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(0).SetRoomExitsId(referedRoom.RoomID(), 0);
                    childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(0).SetRoomExitsNames("Go To " + referedRoom.RoomName(), 0);

                }

                if (m > 0) {

                    referedRoom = childLevels.get(n).GetChildSections().get(m - 1).GetChildRooms()
                            .get(childLevels.get(n).GetChildSections().get(m - 1).GetChildRooms().size() - 1);

                    childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(0).SetRoomExitsId(referedRoom.RoomID(), 3);
                    childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(0).SetRoomExitsNames("Go To " + referedRoom.RoomName(), 3);




                } else if (m == 0) {
                    if (n > 0) {

                        referedRoom = childLevels.get(n - 1).GetChildSections().get(childLevels.get(n - 1).GetChildSections().size()-1).GetChildRooms()
                                .get(childLevels.get(n - 1).GetChildSections().get(childLevels.get(n - 1).GetChildSections().size()-1).GetChildRooms().size() - 1);

                        childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(0).SetRoomExitsId(referedRoom.RoomID(), 3);
                        childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(0).SetRoomExitsNames("Go To " + referedRoom.RoomName(), 3);

                    }
                }



                //Log.d("#######","INJECT FORWARD DOOR REF");
                if (m < childLevels.get(n).GetChildSections().size() - 1) {

                    referedRoom = childLevels.get(n).GetChildSections().get(m + 1).GetChildRooms().get(0);

                    childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).SetRoomExitsId(referedRoom.RoomID(), 0);//sector exit ||level exit||or exit win
                    childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).SetRoomExitsNames("Go To " + referedRoom.RoomName(), 0);

                    //Log.d("*", "ROOM ID: "+String.valueOf(childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).RoomID()));

                    //Log.d("*", "LEVEL: "+String.valueOf(n));Log.d("SECTION", String.valueOf(m));
                    //Log.d("*", "INJECTION: "+String.valueOf(referedRoom.RoomID()));
                    //Log.d("*", "CONFIRMATION: "+String.valueOf(childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).GetRoomExitIdByIndex(0)));


                } else if (m == childLevels.get(n).GetChildSections().size() - 1) {
                    if (n < childLevels.size() - 1) {

                        referedRoom =childLevels.get(n + 1).GetChildSections().get(0).GetChildRooms().get(0);

                        childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).SetRoomExitsId(referedRoom.RoomID(), 0);//sector exit ||level exit||or exit win
                        childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).SetRoomExitsNames("Go To " + referedRoom.RoomName(), 0);

                        //Log.d("*", "ROOM ID: "+String.valueOf(childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).RoomID()));

                        //Log.d("*", "LEVEL: "+String.valueOf(n));Log.d("SECTION", String.valueOf(m));
                        //Log.d("*", "INJECTION: "+String.valueOf(referedRoom.RoomID()));
                        //Log.d("*", "CONFIRMATION: "+String.valueOf(childLevels.get(n).GetChildSections().get(m).GetChildRooms().get(childLevels.get(n).GetChildSections().get(m).GetChildRooms().size() - 1).GetRoomExitIdByIndex(0)));

                    }
                }
            }
        }
    }

    //Make Rooms Connect with a node system ****************************
    private void ConnectRooms() {


        int[] mappedRoomEnter = new int[dungeonTotalLevels * dungeonTotalSections];
        String[] mappedRoomEnterName = new String[dungeonTotalLevels * dungeonTotalSections];
        int[] mappedRoomExit = new int[dungeonTotalLevels * dungeonTotalSections];
        String[] mappedRoomExitName = new String[dungeonTotalLevels * dungeonTotalSections];
        int counterSection = 0;

        //MAPPING ENTER AND EXIT POINTS
        for (int n = 0; n < childLevels.size(); n++) {

            for (int m = 0; m < childLevels.get(n).GetChildSections().size(); m++) {

                DungeonSection actualSection = childLevels.get(n).GetChildSections().get(m);
                int lastRoom = actualSection.GetChildRooms().size()-1;

                    mappedRoomEnter[counterSection] =   actualSection.GetChildRooms().get(0).RoomID();
                    mappedRoomEnterName[counterSection] =   actualSection.GetChildRooms().get(0).RoomName();

                    mappedRoomExit[counterSection] =   actualSection.GetChildRooms().get(lastRoom).RoomID();
                    mappedRoomExitName[counterSection] =   actualSection.GetChildRooms().get(lastRoom).RoomName();

                counterSection++;
            }
        }

        //CONNECTING ENTER AND EXIT POINTS
        counterSection = 0;

        for (int n = 0; n < childLevels.size(); n++) {

            for (int m = 0; m <childLevels.get(n).GetChildSections().size(); m++) {

                DungeonSection actualSection = childLevels.get(n).GetChildSections().get(m);
                int lastRoom = actualSection.GetChildRooms().size()-1;


                if( n != 0 && m != 0) {
                    actualSection.GetChildRooms().get(0).SetRoomExitsId(counterSection <= 0 ? mappedRoomEnter[0] : mappedRoomExit[counterSection - 1], 0);
                    actualSection.GetChildRooms().get(0).SetRoomExitsNames(counterSection <= 0 ? mappedRoomEnterName[0] : mappedRoomExitName[counterSection - 1], 0);
                }

                    actualSection.GetChildRooms().get(lastRoom).SetRoomExitsId(counterSection < mappedRoomExit.length ? mappedRoomEnter[counterSection + 1] : mappedRoomEnter[0], 3);
                    actualSection.GetChildRooms().get(lastRoom).SetRoomExitsNames(counterSection < mappedRoomExit.length ? mappedRoomEnterName[counterSection + 1] : mappedRoomEnterName[0], 3);

                counterSection++;
            }
        }

        //CONNECTION ROOMS
        for (int n = 0; n < childLevels.size(); n++) {

            for (int m = 0; m < childLevels.get(n).GetChildSections().size(); m++) {
                //--

                DungeonSection actualSection = childLevels.get(n).GetChildSections().get(m);
                int actualRoomSize = actualSection.GetChildRooms().size();
                int[] actualSectionRoomsIDs = new int[actualRoomSize];
                int actualRoomDoorFocus = 0;

                //Creates a list of all the Room Ids in this section
                for (int l = 0; l < actualSection.GetChildRooms().size(); l++) {

                    DungeonRoom actualRoom = actualSection.GetChildRooms().get(l);

                    actualSectionRoomsIDs[l] = actualRoom.RoomID();
                }


                for (int l = 0; l < actualSection.GetChildRooms().size(); l++) {



                    DungeonRoom actualRoom = actualSection.GetChildRooms().get(l);
                    int lMaxSize = actualSection.GetChildRooms().size();

                        int doorExitModifier =  lMaxSize <= 1 ? 0 :
                                                l == 0 ? 1 :
                                                l == 1 ? -1 :
                                                lMaxSize < 5 ? -l :
                                                l < 5 ? -2 :
                                                l == lMaxSize-2 ? -5:
                                                l == lMaxSize-1 ? -4:
                                                -2;
                        //Log.d("Modifiers",String.valueOf(doorExitModifier));

                        int doorExitPointer = l + doorExitModifier;
                        //Log.d("Pointer",String.valueOf(doorExitPointer));

                        // doorExitPointer =   doorExitPointer >= lMaxSize ? lMaxSize-1: doorExitPointer;//safety only
                        //doorExitPointer =   doorExitPointer < 0 ? 0: doorExitPointer;//safety only

                        actualRoomDoorFocus = 0;
                        //Log.d("PRE While", "entering...");
                        while(true){
                            if(doorExitPointer >= lMaxSize){ break; } //do not delete
                           // if(doorExitPointer >= actualRoom.GetRoomExitsId().length){ break; } //do not delete
                            if ( actualRoomDoorFocus >= actualRoom.GetRoomExitsId().length){ break; } //do not delete

                            if ( actualSectionRoomsIDs[doorExitPointer] != actualRoom.RoomID() &&
                                    actualRoom.GetRoomExitIdByIndex(actualRoomDoorFocus) == 0 ) {

                                actualRoom.SetRoomExitsId(
                                        actualSection.GetChildRooms().get(doorExitPointer).RoomID(),
                                        actualRoomDoorFocus);
                                actualRoom.SetRoomExitsNames(actualSection.GetChildRooms().get(doorExitPointer).RoomName(),
                                        actualRoomDoorFocus);
                                //Log.d("While IF", "door:"+actualRoomDoorFocus+" id:"+String.valueOf(actualSection.GetChildRooms().get(doorExitPointer).RoomID()));
                            }

                            if (actualRoom.GetRoomExitIdByIndex(actualRoomDoorFocus) != 0 ){
                                actualRoomDoorFocus++;
                            }

                            doorExitPointer++;

                        }

                        //SHUFFLE ACTUAL ROOM'S DOORS IDS
                        List<Integer> doorsIDS = new ArrayList<>(actualRoom.GetRoomExitsId().length);
                        List<String> doorsNames = new ArrayList<>(actualRoom.GetRoomExitsNames().length);
                        int counter = 0;

                        for (int i = 0 ; i < actualRoom.GetRoomExitsId().length; i++) {
                            doorsIDS.add(Integer.valueOf(actualRoom.GetRoomExitIdByIndex(i)));
                            doorsNames.add(actualRoom.GetRoomExitNameByIndex(i));
                        }

                        while(doorsIDS.size()!=0){

                            int  randomNumber = Util.GenerateNumberBetween(0,doorsIDS.size());

                            actualRoom.SetRoomExitsId(doorsIDS.get(randomNumber),counter);
                            actualRoom.SetRoomExitsNames(doorsNames.get(randomNumber),counter);
                            doorsIDS.remove(randomNumber);
                            doorsNames.remove(randomNumber);
                            counter++;
                        }

                }
                    //--
            }
        }
    }

    //-----------------------------------------

    //TEST TOOL
    private void MapDungeonDoors() {


        listDungeonRooms.clear();

        for (DungeonLevel n : childLevels) {
            Log.d("*","Level Name: "+ n.GetDungeonLevelName());
            Log.d("----","----");
            for (DungeonSection m : n.GetChildSections()) {
                Log.d("*","Section Name: "+ m.GetDungeonSectionName());
                Log.d("----","----");
                for (DungeonRoom l : m.GetChildRooms()) {
                    Log.d("####","####");
                    Log.d("*","Room Name: "+ l.RoomName());
                    Log.d("*","Room ID: "+ String.valueOf(l.RoomID()));
                    Log.d("-vv-","-vv-");
                    for (int i = 0; i <4 ; i++) {
                        Log.d("*", "Door Name: "+l.GetRoomExitNameByIndex(i));
                        Log.d("*","Door ID: "+ String.valueOf(l.GetRoomExitIdByIndex(i)));
                    }

                }
            }
        }






    }


    //----------------------------------------

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