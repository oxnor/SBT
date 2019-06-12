package ru.shaa.sbt.shoppingmngr.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskRunOnce extends TaskBase {
    LocalDateTime RunDateTime;

    public TaskRunOnce(int ID, LocalDate begDate, LocalDate endDate, ScheduleType scheduleType, LocalDateTime runDateTime) {
        super(ID, begDate, endDate, scheduleType);
        RunDateTime = runDateTime;
    }
}
