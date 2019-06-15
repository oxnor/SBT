package ru.shaa.sbt.shoppingmngr.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TaskWeekly extends TaskBase{

    LocalTime[] weekDays = new LocalTime[7];

    public LocalTime[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(LocalTime[] weekDays) {
        this.weekDays = weekDays;
    }

    public TaskWeekly(Integer id, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType, LocalTime[] weekDays) {
        super(id, begDate, endDate, scheduleType);
        this.weekDays = weekDays;
    }
}
