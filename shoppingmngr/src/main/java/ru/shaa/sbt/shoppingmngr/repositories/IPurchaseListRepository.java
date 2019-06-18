package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;

public interface IPurchaseListRepository {
    PurchaseList getById(int id);
    void save(PurchaseList task);
    void delete(PurchaseList task);    
}
