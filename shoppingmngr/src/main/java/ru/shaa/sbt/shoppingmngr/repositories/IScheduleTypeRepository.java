package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;

import java.util.List;

public interface IScheduleTypeRepository {
    List<ScheduleType> getAll();
    }

