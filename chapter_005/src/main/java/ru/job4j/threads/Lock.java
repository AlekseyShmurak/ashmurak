package ru.job4j.threads;

public class Lock {
    boolean isLocked = false;

    public void lock() {
        while (isLocked) {
            System.out.println("Замок закрыт для потока");
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        System.out.println("Поток пререхватил управление");
    }

    public void unlock() {
        isLocked = false;
        System.out.println("Замок закрыт");
    }

}
