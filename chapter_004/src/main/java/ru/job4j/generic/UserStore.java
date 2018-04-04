package ru.job4j.generic;


public class UserStore extends AbstractStore<User> {

    public UserStore(int size) {
        this.values = new SimpleList<>(size);
    }

    public synchronized boolean transfer(String fromId, String toId, int amount) {
        boolean rslt = false;
        User first = findById(fromId);
        User second = findById(toId);
        if (amount <= first.getAmount()) {
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
            rslt = true;
        }
        return rslt;
    }
}
