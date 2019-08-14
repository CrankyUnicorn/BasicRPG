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

        NoItem();
        Torch();
        FlaskRadiance();

    }


    private void NoItem(){//TEMPLATE

            Item generatedItem = new Item("No Item",
                    "No Description",
                    0,
                    false);

            itemsList.add(generatedItem);
    }

    private void Torch(){

        Item generatedItem = new Item("Torch",
                "Small flimsy torch to ligth your way",
                5,
                true);

        itemsList.add(generatedItem);
    }
    private void FlaskRadiance(){

        Item generatedItem = new Item("Flask of Radiance",
                "A small flask fill with glowing worms",
                10,
                true);

        itemsList.add(generatedItem);
    }


	//SINGEL ITEM ITEM
    public static Item MementoMori(){

        Item item = new Item(	"Memento Mori",
                "Something from the past",
                1,
                false);


        return item;
    }


    public static Item OldRustyKey(){

        Item item = new Item(   "Old Rusty Key",
                "This old looking key emanates an aura of ill intent.",
                1,
                false);


        return item;
    }


    //ACESSOR
    public static Item GetItemFromList(int index){

        return CloneItem(itemsList.get(index<itemsList.size()?index:0));
    }


    public static Item GetRandomItem(){
        //Log.d("ItemList","Create Item!");
        return CloneItem(itemsList.get(Util.GenerateNumberBetween(0,itemsList.size())));
    }

    public int GetItemsListLength(){
        return itemsList.size();
    }


	//AUX FUNCTS
	
	//AUX CLONER
    public static Item CloneItem (Item _item){

        Item newItemOut = new Item(
				_item.GetItemName(),
                _item.GetItemDescription(),
				_item.getItemQuantity(),
				_item.IsIlluminator()
		);
        
        return newItemOut;
    }

}
