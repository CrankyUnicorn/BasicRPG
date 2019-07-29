package com.example.basicrpg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ItemsList {

    private static List<Item> itemsList = new ArrayList<Item>();

    private static final ItemsList ourInstance = new ItemsList(GameSettings.ITEM_TYPES);//

    public static ItemsList getInstance() {
        return ourInstance;
    }





	//CONSTRUCTOR OVERLOAD
    private ItemsList(int _itemsQuantity){

        NoItem(); //No item



        for(int i = 0 ; i < _itemsQuantity; i++){

            Item generatedItem = new Item(	DungeonNameGenerator.GenerateItemName(),
											"item description");

            itemsList.add(generatedItem);
        }

    }


    private void NoItem(){

            Item generatedItem = new Item("No Item","No Description");

            itemsList.add(generatedItem);
    }

    //ACESSOR
    public static Item KeyItem(){

        Item item = new Item(   "Old Grungy Key",
                "This old looking key emanates an aura of ill intent.");


        return item;
    }


    //ACESSOR
    public static Item GetItemFromList(int index){
        return itemsList.get(index);
    }


    public static  Item GetRandomItem(){
        Log.d("ItemList","Create Item!");
        return itemsList.get(Util.GenerateNumberBetween(0,itemsList.size()));
    }

    public int GetItemsListLength(){
        return itemsList.size();
    }


	//AUX FUNCTS

}
