package ru.shaa.sbt.shoppingmngr.entities;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class TaskBase {
    Integer id;
    LocalDateTime begDate;
    LocalDateTime endDate;
    ScheduleType scheduleType;

    public TaskBase(Integer id, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

}
