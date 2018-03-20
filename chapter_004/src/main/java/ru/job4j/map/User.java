package ru.job4j.map;

import java.util.Calendar;

public class User {
    String name;
    int children;
    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof User && this.name.equals(((User) obj).name)
                && this.children == ((User) obj).children && this.birthday.equals(((User) obj).birthday);
    }
}
