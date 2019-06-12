package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.entities.TaskRunOnce;

import java.sql.Timestamp;
import java.util.Map;

@Component
@Qualifier("TaskRunOnceRepository")
public class TaskRunOnceRepository extends TaskBaseRepository {
    @Autowired
    IScheduleTypeRepository scheduleTypeRepository;

    public TaskRunOnceRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public TaskRunOnce getById(int id) throws ScheduleTypeUnknownException {
        TaskDTO taskDTO = loadTaskDTO(id);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        Map<String, Object> taskPrm = jdbcTemplate.queryForList("appsch.ex_TaskPrmRunOnce @Id_Task = :id", params).get(0);
        ScheduleType scheduleType = new ScheduleType(1, "test", "test");

        TaskRunOnce task = new TaskRunOnce(id, taskDTO.begDate, taskDTO.endDate, scheduleType, ((Timestamp)taskPrm.get("RunDtm")).toLocalDateTime());

        return  task;
    }
}
