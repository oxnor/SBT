package ru.shaa.sbt.shoppingmngr.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TaskWeekly extends TaskBase{
    final LocalTime[] weekDays = new LocalTime[7];

    public TaskWeekly(int id, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType) {
        super(id, begDate, endDate, scheduleType);
    }
}
