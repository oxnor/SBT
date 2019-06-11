package ru.shaa.sbt.shoppingmngr.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.Entities.TaskBase;

import java.util.List;
import java.util.Map;

@Component
public class TaskRepository implements ITaskRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public TaskBase GetById(int ID) throws ScheduleTypeUnknownException{
        List<Map<String, Object>> dr = jdbcTemplate.queryForList("appsch.ex_Tasks");
        String SchTypeCode = dr.get(0).get("SchTypeCode").toString();
        switch (SchTypeCode)
        {
            case "RUN_ONCE":;break;
            case "WEEKLY":;break;
            default: throw new ScheduleTypeUnknownException("Неизвестный тип расписания");
        }
        return null;
    }
}
