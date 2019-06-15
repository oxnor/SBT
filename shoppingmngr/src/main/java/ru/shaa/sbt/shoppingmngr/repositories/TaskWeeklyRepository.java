package ru.shaa.sbt.shoppingmngr.repositories;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.entities.TaskBase;
import ru.shaa.sbt.shoppingmngr.entities.TaskWeekly;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Component
@Qualifier("TaskWeeklyRepository")
public class TaskWeeklyRepository extends TaskBaseRepository {
    @Autowired
    IScheduleTypeRepository scheduleTypeRepository;

    public TaskWeeklyRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        super(jdbcTemplate, dataSource);
    }

    @Override
    public void save(TaskBase task) {
        TaskWeekly taskWeekly = (TaskWeekly)task;
        super.save(task);
        prmSave(taskWeekly);
    }

    private void prmSave(TaskWeekly task) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("TaskPrmWeeklyCrUpd");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("Id_Task", task.getId());

        for (int i=0; i < task.getWeekDays().length;i++)
            params.addValue(String.format("tm%d", i), task.getWeekDays()[i]);

        jdbcCall.execute(params);
    }
/*
    @Override
    public void delete(int id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("TaskPrmRunOnceDel");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("Id_Task", id);

        jdbcCall.execute(params);
        super.delete(id);
    }
*/
    @Override
    public TaskWeekly getById(int id) {
        TaskDTO taskDTO = loadTaskDTO(id);
        if (taskDTO == null) return null;
        LocalTime[] weekDays = loadPrmDTO(id);
        ScheduleType scheduleType = scheduleTypeRepository.getByCode(taskDTO.schType);

        TaskWeekly task = new TaskWeekly(id, taskDTO.begDate, taskDTO.endDate, scheduleType, weekDays);

        return  task;
    }

    LocalTime[] loadPrmDTO(int idTask)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("ex_TaskPrmWeekly");
        jdbcCall.returningResultSet("rs"
                , ((rs, i) -> new TaskPrmWeeklyDTO(
                          rs.getInt("WeekDay")
                        , rs.getTime("RunTime").toLocalTime()
                )
                )
        );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("Id_Task", idTask );

        Map<String, Object> out = jdbcCall.execute(params);
        List<TaskPrmWeeklyDTO> prmDTO = (List<TaskPrmWeeklyDTO>)out.get("rs");
        LocalTime[] weekDay = new LocalTime[7];
        for (TaskPrmWeeklyDTO dtm: prmDTO)
            weekDay[dtm.weekDay] = dtm.runTime;

        return weekDay;
    }

    class TaskPrmWeeklyDTO
    {
        int weekDay;
        LocalTime runTime;

        TaskPrmWeeklyDTO(int weekDay, LocalTime runTime)
        {
            this.weekDay = weekDay;
            this.runTime = runTime;
        }
    }
}
