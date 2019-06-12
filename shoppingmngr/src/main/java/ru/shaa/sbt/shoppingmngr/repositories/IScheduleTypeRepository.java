package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;

import java.util.List;

public interface IScheduleTypeRepository {
    ScheduleType getById(int id);
    ScheduleType getByCode(String code);
    List<ScheduleType> getAll();
    }

