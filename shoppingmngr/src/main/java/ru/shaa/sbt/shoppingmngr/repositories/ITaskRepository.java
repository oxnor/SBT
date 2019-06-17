package ru.shaa.sbt.shoppingmngr.repositories;

import ru.shaa.sbt.shoppingmngr.entities.TaskBase;

public interface ITaskRepository {
    TaskBase getById(int id);
    void save(TaskBase task);
    void delete(TaskBase task);

    class ScheduleTypeUnknownException extends Exception
    {
        public ScheduleTypeUnknownException(String msg)
        {
            super(msg);
        }
    }
}
