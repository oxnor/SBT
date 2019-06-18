package ru.shaa.sbt.shoppingmngr.entities;

public class Goods {
    Integer id;
    String caption;

    public Goods(Integer id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}
