package ru.shaa.sbt.shoppingmngr.entities;

import java.time.LocalDateTime;

public class TaskRunOnce extends TaskBase {
    LocalDateTime runDateTime;

    public TaskRunOnce(int id, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType, LocalDateTime runDateTime) {
        super(id, begDate, endDate, scheduleType);
        this.runDateTime = runDateTime;
    }
}
