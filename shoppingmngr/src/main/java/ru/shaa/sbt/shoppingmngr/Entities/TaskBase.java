package ru.shaa.sbt.shoppingmngr.Entities;

import java.time.LocalDate;

public abstract class TaskBase {
    int ID;
    LocalDate BegDate;
    LocalDate EndDate;
    ScheduleType scheduleType;

    public TaskBase(int ID, LocalDate begDate, LocalDate endDate, ScheduleType scheduleType) {
        this.ID = ID;
        BegDate = begDate;
        EndDate = endDate;
        this.scheduleType = scheduleType;
    }

    public LocalDate getBegDate() {
        return BegDate;
    }

    public void setBegDate(LocalDate begDate) {
        BegDate = begDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
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
