package ru.job4j.generic;

public class RoleStore extends AbstractStore {

    public RoleStore(int size) {
        this.values = new SimpleList<Role>(size);
    }

    @Override
    public void add(Base model) {
        if (model instanceof Role) {
            super.add(model);
        }
    }

    @Override
    public boolean replace(String id, Base model) {
        return  model instanceof Role && super.replace(id, model);
    }
}