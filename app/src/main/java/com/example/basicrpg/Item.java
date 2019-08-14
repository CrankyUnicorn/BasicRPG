package com.example.basicrpg;

public class Item {

    private String itemName;
    private String itemDescription;
    private int itemQuantity;
	private boolean itemIlluminator;

	Item(String _itemName,String _itemDescription, int _itemQuantity, boolean _itemIlluminator){
	
		this.itemName = _itemName;
		this.itemDescription = _itemDescription;
		this.itemQuantity = _itemQuantity;
		this.itemIlluminator = _itemIlluminator;
	
	}

	//Name and Description
    public String GetItemName() {
        return itemName;
    }

    public void SetItemName(String itemName) {
        this.itemName = itemName;
    }

    public String GetItemDescription() {
        return itemDescription;
    }

    public void SetItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    //Illumination
	public boolean IsIlluminator(){
		return itemIlluminator;
	}


	//Quantity
    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int _itemQuantity) {

	    this.itemQuantity += _itemQuantity;

        if (this.itemQuantity < 0){
            this.itemQuantity = 0;
        }
    }
}
