package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;

public interface IPlannedPurchaseRepository {
    PlannedPurchase getById(int id, PurchaseList purchaseList) throws ViolationСonsistencyException;
    void save(PlannedPurchase plannedPurchase);
    void delete(PlannedPurchase plannedPurchase);
}

