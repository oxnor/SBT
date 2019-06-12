package ru.shaa.sbt.shoppingmngr.Repositories;

import com.fasterxml.jackson.databind.BeanProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.Entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.Entities.TaskBase;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskBaseRepository implements ITaskRepository {
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TaskBaseRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TaskDTO LoadTaskDTO(int ID)
    {
        RowMapper<TaskDTO> taskDTORowMapper = (ResultSet resultSet, int rowNum) -> {
            TaskDTO t = new TaskDTO();
            t.ID = resultSet.getInt("id");
            t.BegDate = resultSet.getTimestamp("BegDtm").toLocalDateTime();
            t.EndDate = resultSet.getTimestamp("EndDtm").toLocalDateTime();
            t.SchType = resultSet.getString("SchTypeCode");
            return t;
        };

        //Map<String, Object> params = new HashMap<String, Object>();
        //params.put("ID", ID);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", ID);
        TaskDTO taskDTO = jdbcTemplate.query("appsch.ex_Tasks @Id_Task = :ID", params, taskDTORowMapper).get(0);
        return taskDTO;
    }

    @Override
    public TaskBase GetById(int ID) throws ScheduleTypeUnknownException {
        return null;
    }

    public class TaskDTO
    {
        int ID;
        LocalDateTime BegDate;
        LocalDateTime EndDate;
        String SchType;
    }
}
