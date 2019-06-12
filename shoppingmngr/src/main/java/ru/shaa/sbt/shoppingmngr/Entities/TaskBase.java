package ru.shaa.sbt.shoppingmngr.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class TaskBase {
    int ID;
    LocalDateTime BegDate;
    LocalDateTime EndDate;
    ScheduleType scheduleType;

    public TaskBase(int ID, LocalDateTime begDate, LocalDateTime endDate, ScheduleType scheduleType) {
        this.ID = ID;
        BegDate = begDate;
        EndDate = endDate;
        this.scheduleType = scheduleType;
    }

    public LocalDateTime getBegDate() {
        return BegDate;
    }

    public void setBegDate(LocalDateTime begDate) {
        BegDate = begDate;
    }

    public LocalDateTime getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        EndDate = endDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

}
