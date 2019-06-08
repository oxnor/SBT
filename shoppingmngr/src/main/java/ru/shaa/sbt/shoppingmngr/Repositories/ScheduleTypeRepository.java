package ru.shaa.sbt.shoppingmngr.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.Entities.ScheduleType;

import java.util.List;

@Component
public class ScheduleTypeRepository implements IScheduleTypeRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleTypeRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ScheduleType> GetAll() {
        return jdbcTemplate.query("SELECT * FROM appsch.rb_SchType rst", ROW_MAPPER);
    }
}
