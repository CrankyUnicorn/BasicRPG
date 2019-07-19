package com.example.basicrpg;

import java.util.Random;

public class Util {

    private static int currentID;

    //Aux
    public static int GenerateNumberBetween(int _min, int _max){
        Random r = new Random();
        int result = r.nextInt(_max-_min) + _min;
        return result;
    }

    //Aux
    public static int GetId(){

        currentID++;

        return currentID;
    }


}
