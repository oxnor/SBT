package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.Owner;

public interface IOwnerRepository {
    Owner getById(int id);
}

