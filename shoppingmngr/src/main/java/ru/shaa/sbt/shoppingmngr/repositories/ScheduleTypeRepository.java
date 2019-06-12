package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;

import java.sql.ResultSet;
import java.util.List;

@Component
public class ScheduleTypeRepository implements IScheduleTypeRepository {
    RowMapper<ScheduleType> scheduleTypeRowMapper = (ResultSet resultSet, int rowNum) -> new ScheduleType(
                  resultSet.getInt("id")
                , resultSet.getString("code")
                , resultSet.getString("caption")
    );
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleTypeRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ScheduleType> getAll() {
        return jdbcTemplate.query("appsch.ex_SchTypes", scheduleTypeRowMapper);
    }
}
