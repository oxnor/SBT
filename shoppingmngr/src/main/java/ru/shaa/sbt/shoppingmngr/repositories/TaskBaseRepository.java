package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.TaskBase;

import java.sql.ResultSet;
import java.time.LocalDateTime;

@Component
@Qualifier("TaskBaseRepository")
public class TaskBaseRepository implements ITaskRepository {
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TaskBaseRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
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

    public class TaskDTO
    {
        int id;
        LocalDateTime begDate;
        LocalDateTime endDate;
        String schType;
    }
}
