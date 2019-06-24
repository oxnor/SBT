package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;

import java.util.List;

public interface IPlannedPurchaseRepository {
    PlannedPurchase getById(int id, PurchaseList purchaseList);
    List<PlannedPurchase> getByPurchaseList(PurchaseList purchaseList);
    void save(PlannedPurchase plannedPurchase);
    void delete(PlannedPurchase plannedPurchase);
}

