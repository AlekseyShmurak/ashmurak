package ru.job4j.generic;


public class UserStore extends AbstractStore<User> {

    public UserStore(int size) {
        this.values = new SimpleList<>(size);
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

    @Override
    public synchronized User findById(String id) {
        return (User) super.findById(id);
    }

    public boolean transfer(String fromId, String toId, int amount) {
        boolean rslt = false;
        User first = (User) super.findById(fromId);
        User second = (User) super.findById(toId);
        if (amount <= first.getAmount()) {
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
            rslt = true;
        }
        return rslt;
    }
}
