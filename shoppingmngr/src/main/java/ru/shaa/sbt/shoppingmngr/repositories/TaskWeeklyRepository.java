package ru.shaa.sbt.shoppingmngr.repositories;

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

    @Override
    public TaskWeekly getById(int id) {
        TaskDTO taskDTO = loadTaskDTO(id);
        if (taskDTO == null) return null;
        TaskPrmRunOnceDTO prmDTO = loadPrmDTO(id);
        ScheduleType scheduleType = scheduleTypeRepository.getByCode(taskDTO.schType);

        //TODO кидать исключение если prmDTO = null
        TaskWeekly task = new TaskWeekly(id, taskDTO.begDate, taskDTO.endDate, scheduleType, prmDTO != null? prmDTO.runDtm: null);

        return  task;
    }

    TaskPrmRunOnceDTO loadPrmDTO(int idTask)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("ex_TaskPrmRunOnce");
        jdbcCall.returningResultSet("rs"
                , ((rs, i) -> new TaskPrmRunOnceDTO(
                        rs.getInt("Id_Task")
                        , rs.getTimestamp("RunDtm").toLocalDateTime()
                )
                )
        );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("Id_Task", idTask );

        Map<String, Object> out = jdbcCall.execute(params);
        List<TaskPrmRunOnceDTO> prmDTO = (List<TaskPrmRunOnceDTO>)out.get("rs");
        if (prmDTO != null && prmDTO.isEmpty())
        {
            return prmDTO.get(0);
        }
        return null;
    }*/

    class TaskPrmRunOnceDTO
    {
        int idTask;
        LocalDateTime runDtm;

        TaskPrmRunOnceDTO(int idTask, LocalDateTime runDtm)
        {
            this.idTask = idTask;
            this.runDtm = runDtm;
        }
    }
}
