package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleTypeRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ScheduleType getById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query("appsch.ex_SchTypes @Id_SchType = :id", params, scheduleTypeRowMapper).get(0);
    }

    @Override
    public ScheduleType getByCode(String code) {
        if (code == null) return null;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", code);
        return jdbcTemplate.query("appsch.ex_SchTypes @SchTypeCode = :code", params, scheduleTypeRowMapper).get(0);
    }

    @Override
    public List<ScheduleType> getAll() {
        return jdbcTemplate.query("appsch.ex_SchTypes", scheduleTypeRowMapper);
    }
}
