package ru.shaa.sbt.shoppingmngr.entities;

import java.time.LocalDateTime;

/**
 * Планируемая покупка
 */
public class FactPurchase {
    Integer id;
    Owner owner;
    Goods goods;
    LocalDateTime purchaseDate;

    public FactPurchase(Integer id, Owner owner, Goods goods, LocalDateTime purchaseDate) {
        this.id = id;
        this.owner = owner;
        this.goods = goods;
        this.purchaseDate = purchaseDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


}
