package ru.shaa.sbt.shoppingmngr.entities;

import java.time.LocalDateTime;

public class TaskRunOnce extends TaskBase {
    LocalDateTime runDateTime;

    public TaskRunOnce(Integer id, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType, LocalDateTime runDateTime) {
        super(id, begDate, endDate, scheduleType);
        this.runDateTime = runDateTime;
    }

    public LocalDateTime getRunDateTime() {
        return runDateTime;
    }

    public void setRunDateTime(LocalDateTime runDateTime) {
        this.runDateTime = runDateTime;
    }


}
