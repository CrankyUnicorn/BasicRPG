package com.example.basicrpg;

public class Item {

    private String itemName;
    private String itemDescription;

	Item(String _itemName,String _itemDescription){
	
		this.itemName = _itemName;
		this.itemDescription = _itemDescription;
	
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
}
