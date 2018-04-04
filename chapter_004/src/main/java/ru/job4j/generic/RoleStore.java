package ru.job4j.generic;

public class RoleStore extends AbstractStore<Role> {

    public RoleStore(int size) {
        this.values = new SimpleList<Role>(size);
    }

}