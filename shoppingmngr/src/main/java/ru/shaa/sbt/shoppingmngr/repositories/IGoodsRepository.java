package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.Goods;

public interface IGoodsRepository {
    Goods getById(int id);
    void save(Goods task);
    void delete(Goods task);
}

