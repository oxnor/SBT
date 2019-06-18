package ru.shaa.sbt.shoppingmngr.entities;

public class PurchaseList {
    Integer id;
    String caption;
    Owner owner;

    public PurchaseList(Integer id, String caption, Owner owner) {
        this.id = id;
        this.caption = caption;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


}
