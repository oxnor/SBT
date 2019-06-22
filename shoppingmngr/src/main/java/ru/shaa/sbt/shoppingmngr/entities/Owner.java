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
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        return this.id.equals(((Owner)obj).id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
