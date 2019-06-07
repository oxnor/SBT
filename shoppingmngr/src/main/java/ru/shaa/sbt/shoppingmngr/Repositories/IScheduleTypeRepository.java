package ru.shaa.sbt.shoppingmngr.Repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.shaa.sbt.shoppingmngr.Entities.ScheduleType;

import java.sql.ResultSet;
import java.util.List;

public interface IScheduleTypeRepository {
    RowMapper<ScheduleType> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new ScheduleType(resultSet.getInt("id")
                , resultSet.getString("code")
                , resultSet.getString("caption"));};

    List<ScheduleType> GetAll();
    };

