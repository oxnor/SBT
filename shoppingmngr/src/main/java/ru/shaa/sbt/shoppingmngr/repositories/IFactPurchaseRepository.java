package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.FactPurchase;
import ru.shaa.sbt.shoppingmngr.entities.Owner;

import java.util.List;

public interface IFactPurchaseRepository {
    FactPurchase getById(int id, Owner owner);
    List<FactPurchase> getByOwner(Owner owner);
    void save(FactPurchase factPurchase);
    void delete(FactPurchase factPurchase);
}

