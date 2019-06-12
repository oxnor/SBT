package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.TaskBase;

public interface ITaskRepository {
    TaskBase getById(int id) throws ScheduleTypeUnknownException;

    class ScheduleTypeUnknownException extends Exception
    {
        public ScheduleTypeUnknownException(String msg)
        {
            super(msg);
        }
    }
}
