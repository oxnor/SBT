package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;

public interface IPlannedPurchaseRepository {
    PlannedPurchase getById(int id);
    void save(PlannedPurchase task);
    void delete(PlannedPurchase task);
}

