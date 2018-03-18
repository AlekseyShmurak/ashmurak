package ru.job4j.generic;

public class UserStore extends AbstractStore {

    public UserStore(int size) {
        this.values = new SimpleList<User>(size);
    }

    @Override
    public void add(Base model) {
        if (model instanceof User) {
            super.add(model);
        }
    }

    @Override
    public boolean replace(String id, Base model) {
        return  model instanceof User && super.replace(id, model);
    }
}
