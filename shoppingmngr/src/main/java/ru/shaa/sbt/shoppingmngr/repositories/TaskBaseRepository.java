package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.TaskBase;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Map;

@Component
@Qualifier("TaskBaseRepository")
public class TaskBaseRepository implements ITaskRepository {
    protected NamedParameterJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    @Autowired
    public TaskBaseRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    public TaskDTO loadTaskDTO(int id)
    {
        RowMapper<TaskDTO> taskDTORowMapper = (ResultSet resultSet, int rowNum) -> {
            TaskDTO t = new TaskDTO();
            t.id = resultSet.getInt("id");
            t.begDate = resultSet.getTimestamp("BegDtm").toLocalDateTime();
            t.endDate = resultSet.getTimestamp("EndDtm").toLocalDateTime();
            t.schType = resultSet.getString("SchTypeCode");
            return t;
        };

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        TaskDTO taskDTO = jdbcTemplate.query("appsch.ex_Tasks @Id_Task = :id", params, taskDTORowMapper).get(0);
        return taskDTO;
    }

    @Override
    public TaskBase getById(int id) throws ScheduleTypeUnknownException {
        return null;
    }

    @Override
    public void save(TaskBase task) {
        if (task.getId() == null)
            create(task);
        else 
            update(task);
    }

    private void create(TaskBase task) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("TaskCreate");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("SchTypeCode", task.getScheduleType().getCode());
        params.addValue("BegDtm", task.getBegDate());
        params.addValue("EndDtm", task.getEndDate());

        Map<String, Object> out = jdbcCall.execute(params);

        task.setId((Integer) out.get("ID"));
    }

    private void update(TaskBase task) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("TaskUpdate");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("ID", task.getId());
        params.addValue("SchTypeCode", task.getScheduleType().getCode());
        params.addValue("BegDtm", task.getBegDate());
        params.addValue("EndDtm", task.getEndDate());

        Map<String, Object> out = jdbcCall.execute(params);
    }

    public class TaskDTO
    {
        int id;
        LocalDateTime begDate;
        LocalDateTime endDate;
        String schType;
    }
}
