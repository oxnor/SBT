package ru.shaa.sbt.shoppingmngr.Entities;

public class ScheduleType
{
    public ScheduleType(int ID, String Code, String Caption)
    {
        this.ID = ID;
        this.Code = Code;
        this.Caption = Caption;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    int     ID;
    String  Code;
    String  Caption;
}
