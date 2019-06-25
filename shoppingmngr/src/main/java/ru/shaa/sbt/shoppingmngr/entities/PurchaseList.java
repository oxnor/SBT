package ru.shaa.sbt.shoppingmngr.entities;

import java.util.ArrayList;
import java.util.List;

public class PurchaseList {
    Integer id;
    String caption;
    Owner owner;

    List<PlannedPurchase> plannedPurchases;

    public PurchaseList(Integer id, String caption, Owner owner) {
        this.id = id;
        this.caption = caption;
        this.owner = owner;
        this.plannedPurchases = new ArrayList<>();
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

    public List<PlannedPurchase> getPlannedPurchases() {
        return plannedPurchases;
    }

    public void setPlannedPurchases(List<PlannedPurchase> plannedPurchases) {
        this.plannedPurchases = plannedPurchases;
    }

}
