package com.example.basicrpg;

import android.util.Log;

import java.util.Random;

public class Util {

    private static int currentID;

    private static int currentRoomID;

    //Aux
    public static int GenerateNumberBetween(int _min, int _max){
        //Log.d("Number Generator","Start Generating!");
        //Log.d("min:", String.valueOf(_min));
        //Log.d("max:", String.valueOf(_max));

        Random r = new Random();
        int result;
        if(_min == _max){
            result = _max;
        }else{
            result = r.nextInt(_max-_min) + _min;
        }



        //Log.d("result :",String.valueOf(result));
        return result;
    }

    //Aux
    public static int GetId(){

        currentID++;

        if(currentID==Integer.MAX_VALUE){
            Log.d("Alert: Max Value", String.valueOf(currentID));
        }

        return currentID;
    }

    //Aux
    public static int GetRoomId(){

        currentRoomID++;

        if(currentID==Integer.MAX_VALUE){
            Log.d("Alert: Max Value", String.valueOf(currentID));
        }

        return currentRoomID;
    }


}
