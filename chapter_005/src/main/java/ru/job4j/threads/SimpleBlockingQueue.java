package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private int limit = 3;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            System.out.println("queue is full");
            wait();
        }
        queue.offer(value);
        this.notify();
        System.out.println("offer " + value);
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            System.out.println("queue is empty");
            wait();
        }
        T rslt = queue.poll();
        System.out.println("poll " + rslt);
        this.notify();
        return rslt;
    }
}
