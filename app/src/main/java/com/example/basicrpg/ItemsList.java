package com.example.basicrpg;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ItemsList {

    private static List<Item> itemsList = new ArrayList<Item>();

    private static final ItemsList ourInstance = new ItemsList();//

    public static ItemsList getInstance() {
        return ourInstance;
    }





	//CONSTRUCTOR OVERLOAD
    private ItemsList(){
    //ADDS ITEMS TO LISTING FOR RANDOM TREASURE USE OR LOT

        Torch();
        FlaskRadiance();

    }


    private void NoItem(){//TEMPLATE

            Item generatedItem = new Item("No Item",
                    "No Description",
                    false);

            itemsList.add(generatedItem);
    }

    private void Torch(){

        Item generatedItem = new Item("Torch",
                "Small flimsy torch to ligth your way",
                true);

        itemsList.add(generatedItem);
    }
    private void FlaskRadiance(){

        Item generatedItem = new Item("Flask of Radiance",
                "A small flask fill with glowing worms",
                true);

        itemsList.add(generatedItem);
    }

//SINGEL ITEM ITEM
    public static Item MementoMori(){

        Item item = new Item(	"Memento Mori",
                "Something from the past",
                false);


        return item;
    }


    public static Item OldRustyKey(){

        Item item = new Item(   "Old Rusty Key",
                "This old looking key emanates an aura of ill intent.",
                false);


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
