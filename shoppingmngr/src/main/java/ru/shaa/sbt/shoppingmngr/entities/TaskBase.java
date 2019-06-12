package ru.shaa.sbt.shoppingmngr.entities;

import java.time.LocalDateTime;

public abstract class TaskBase {
    int id;
    LocalDateTime begDate;
    LocalDateTime endDate;
    ScheduleType scheduleType;

    public TaskBase(int id, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType) {
        this.id = id;
        this.begDate = begDate;
        this.endDate = endDate;
        this.scheduleType = scheduleType;
    }

    public LocalDateTime getBegDate() {
        return begDate;
    }

    public void setBegDate(LocalDateTime begDate) {
        this.begDate = begDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

}
