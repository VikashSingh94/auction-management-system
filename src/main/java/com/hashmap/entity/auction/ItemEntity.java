package com.hashmap.entity.auction;

import com.hashmap.models.Item;

import javax.persistence.Embeddable;

@Embeddable
public class ItemEntity {

    private String name;
    private String description;

    protected ItemEntity(){
    }

    public ItemEntity(Item item) {
        this.name = item.getName();
        this.description = item.getDescription();
    }


    Item toData(){
        return new Item(this.name,this.description);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
