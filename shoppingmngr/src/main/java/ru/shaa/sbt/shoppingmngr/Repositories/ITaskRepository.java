package ru.shaa.sbt.shoppingmngr.Repositories;

import ru.shaa.sbt.shoppingmngr.Entities.TaskBase;

public interface ITaskRepository {
    TaskBase GetById(int ID) throws ScheduleTypeUnknownException;

    class ScheduleTypeUnknownException extends Exception
    {
        public ScheduleTypeUnknownException(String msg)
        {
            super(msg);
        }
    }
}
