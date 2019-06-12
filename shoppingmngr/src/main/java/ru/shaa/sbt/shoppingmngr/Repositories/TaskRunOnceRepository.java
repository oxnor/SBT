package ru.shaa.sbt.shoppingmngr.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.shaa.sbt.shoppingmngr.Entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.Entities.TaskBase;
import ru.shaa.sbt.shoppingmngr.Entities.TaskRunOnce;

import javax.management.Query;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class TaskRunOnceRepository extends TaskBaseRepository {
    @Autowired
    IScheduleTypeRepository scheduleTypeRepository;

    public TaskRunOnceRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public TaskBase GetById(int ID) throws ScheduleTypeUnknownException {
        TaskDTO taskDTO = LoadTaskDTO(ID);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", ID);
        Map<String, Object> taskPrm = jdbcTemplate.queryForList("appsch.ex_TaskPrmRunOnce @Id_Task = :ID", params).get(0);
        ScheduleType scheduleType = new ScheduleType(1, "test", "test");

        TaskRunOnce task = new TaskRunOnce(ID, taskDTO.BegDate, taskDTO.EndDate, scheduleType, ((Timestamp)taskPrm.get("RunDtm")).toLocalDateTime() )

        return  task;
    }
}
