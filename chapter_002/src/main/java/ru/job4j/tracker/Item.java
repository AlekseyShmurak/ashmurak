package ru.job4j.tracker;

import java.time.LocalDateTime;

public class Item {
    private int id;
    private String name;
    private String desc;
    private String created;
    private String[] comments;

    Item() {
        name = "bla";
        desc = "blabla";
    }

    Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        setCreated();
    }

    Item(String name, String desc, String created) {
        this.name = name;
        this.desc = desc;
        setCreated();
        this.created = created;
    }

    Item(int id, String name, String desc, String created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        setCreated();
        this.created = created;
    }


    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated() {
        this.created = String.valueOf(LocalDateTime.now());
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String[] getComments() {
        return comments;
    }
    public void setComments(String[] comments) {
        this.comments = comments;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
