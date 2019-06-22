package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;

public interface IPlannedPurchaseRepository {
    PlannedPurchase getById(int id);
    void save(PlannedPurchase plannedPurchase);
    void delete(PlannedPurchase plannedPurchase);
}

