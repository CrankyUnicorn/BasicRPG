package com.example.basicrpg;

import java.util.ArrayList;
import java.util.List;

public class DungeonSection {

    public int dungeonSectionId;

    public int numberOfRooms;

    public List<DungeonRoom> thisDungeonSection = new ArrayList<DungeonRoom>();


    public void DungeonSection(int _numberOfRooms){

        numberOfRooms=_numberOfRooms;

        setDungeonSection(_numberOfRooms);
    }


    private void setDungeonSection(int _numberOfRooms){

        for (int i = 0; i <_numberOfRooms; i++){

            DungeonRoom dungeonRoom = new DungeonRoom();

            dungeonRoom.roomId = 24;
            dungeonRoom.roomName = "Room name";
            dungeonRoom.roomDescription = "Room name";
            dungeonRoom.roomExitsId = new int[] {44,35,34,445,42};
            dungeonRoom.roomExitsNames = new String[] {"Torture Room", "Tower"};//posso substituir isto por uma Map<String, int> Exits  being the name the button name  or description and the number the pointer to the room id

            thisDungeonSection.add(dungeonRoom);
        }
    }
}

/*public class Tweet {

    List<Tweet> tweets = new ArrayList<Tweet>();
    String title;
    String body;

    public Tweet() {
        setTweets();
    }

    private void setTweets() {
        for(int i = 0; i <20; i++ ) {
            Tweet tweet = new Tweet();
            tweet.setTitle("A nice header for Tweet # " +i);
            tweet.setBody("Some random body text for the tweet # " +i);
            tweets.add(tweet);
        }
        TweetAdapter tweetItemArrayAdapter = new TweetAdapter(this, new String[10]);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

}*/