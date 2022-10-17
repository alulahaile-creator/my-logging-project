package com.example.application.data.entity;

import java.util.List;

public class Items {

    public List<Item> itemTypes;

    public List<Item> getItemType() {
        return itemTypes;
    }

    public void setItemType(List<Item> itemTypeTobeSet){
        this.itemTypes = itemTypeTobeSet;
    }

    public Items( List<Item> itemTypes) {
        this.itemTypes = itemTypes;
    }
}
