package ru.shaa.sbt.shoppingmngr.entities;

public class ScheduleType
{
    int id;
    String code;
    String caption;

    public ScheduleType(int id, String code, String caption)
    {
        this.id = id;
        this.code = code;
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}
