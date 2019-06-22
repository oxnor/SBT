package ru.shaa.sbt.shoppingmngr.entities;

public class Owner {
    Integer id;
    String caption;

    public Owner(Integer id, String caption) {
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

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Owner)obj).id);
    }
}
