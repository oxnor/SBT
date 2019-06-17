package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.TaskBase;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component("TaskBase")
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
            t.id = resultSet.getInt("ID");
            t.begDate = resultSet.getTimestamp("BegDtm").toLocalDateTime();
            t.endDate = resultSet.getTimestamp("EndDtm").toLocalDateTime();
            t.schType = resultSet.getString("SchTypeCode");
            return t;
        };

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("ex_Tasks");
        jdbcCall.returningResultSet("rs", taskDTORowMapper);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("Id_Task", id);

        Map<String, Object> out = jdbcCall.execute(params);
        List<TaskDTO> taskDTOList = (List<TaskDTO>)out.get("rs");
        if (taskDTOList != null && !taskDTOList.isEmpty())
        {
            return taskDTOList.get(0);
        }
        return null;
    }


    @Override
    public TaskBase getById(int id){
        throw new UnsupportedOperationException("Not supported yet.");
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

    @Override
    public void delete(TaskBase task) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("appsch").withProcedureName("TaskDelete");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", task.getId());

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
