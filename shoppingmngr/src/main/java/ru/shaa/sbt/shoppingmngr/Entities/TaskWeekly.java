package ru.shaa.sbt.shoppingmngr.Entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TaskWeekly extends TaskBase{
    final LocalTime[] WeekDays = new LocalTime[7];

    public TaskWeekly(int ID, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType) {
        super(ID, begDate, endDate, scheduleType);
    }
}
