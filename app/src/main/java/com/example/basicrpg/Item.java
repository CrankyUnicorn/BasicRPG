package com.example.basicrpg;

public class Item {

    private String itemName;
    private String itemDescription;
	private boolean itemIlluminator;

	Item(String _itemName,String _itemDescription, boolean _itemIlluminator){
	
		this.itemName = _itemName;
		this.itemDescription = _itemDescription;
		this.itemIlluminator = _itemIlluminator;
	
	}

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
	
	public boolean IsIlluminator(){
		return itemIlluminator;
	}
}
