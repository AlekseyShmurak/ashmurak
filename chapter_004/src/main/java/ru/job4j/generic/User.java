package ru.job4j.generic;

import net.jcip.annotations.GuardedBy;

public class User extends Base {

    @GuardedBy("this")
    private int amount;


    protected User(String id) {
        super(id);
        this.amount = 0;
    }

    protected User(String id, int amount) {
        super(id);
        this.amount = amount;
    }

    public synchronized int getAmount() {
        return amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }
}
