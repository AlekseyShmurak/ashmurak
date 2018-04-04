package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Lock {
    @GuardedBy("this")
    private boolean isLocked = false;
    @GuardedBy("this")
    private Thread crntThread;

    public synchronized void lock() {
        while (isLocked) {
            System.out.println("Замок закрыт для потока");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        crntThread = Thread.currentThread();
        System.out.println("Поток пререхватил управление");
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == crntThread) {
            isLocked = false;
            this.notifyAll();
            System.out.println("Замок закрыт");
        }
    }

}
