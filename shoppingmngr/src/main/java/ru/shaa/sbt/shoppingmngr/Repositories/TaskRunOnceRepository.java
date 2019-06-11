package ru.shaa.sbt.shoppingmngr.Repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.shaa.sbt.shoppingmngr.Entities.TaskBase;
import ru.shaa.sbt.shoppingmngr.Entities.TaskRunOnce;

public class TaskRunOnceRepository implements ITaskRepository {
    private JdbcTemplate jdbcTemplate;

    @Override
    public TaskBase GetById(int ID) throws ScheduleTypeUnknownException {
        TaskRunOnce task = new TaskRunOnce();
        jdbcTemplate.
        return  task;
    }
}
