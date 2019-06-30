package ru.shaa.sbt.shoppingmngr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Планируемая покупка
 */
public class PlannedPurchase {
    Integer id;
    @JsonIgnore
    PurchaseList purchaseList;
    Goods goods;
    TaskBase task;  //TODO: заменить на ITask?
    boolean isCompleted;
    boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PurchaseList getPurchaseList() {
        return purchaseList;
    }

    public Goods getGoods() {
        return goods;
    }

    public TaskBase getTask() {
        return task;
    }

    public void setTask(TaskBase task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public PlannedPurchase(Integer id, PurchaseList purchaseList, Goods goods, TaskBase task, boolean isCompleted, boolean isDeleted) {
        this.id = id;
        this.purchaseList = purchaseList;
        this.goods = goods;
        this.task = task;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
    }
}
