package ru.job4j.threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

public class ThreadPool {
    private int threadsLim = Runtime.getRuntime().availableProcessors();
    private boolean isActive = false;
    private Queue<Runnable> tasks = new  ConcurrentLinkedQueue<>();
    private final Object lock = new Object();



    public void addTask(Runnable task) {
        synchronized (lock) {
            this.tasks.offer(task);
            lock.notify();
        }
    }

    public  void finishWorking() {
        this.isActive = false;
        synchronized (lock) {
            lock.notify();
        }
    }

    public void execute() {
        isActive = true;
        for (int i = 0; i < threadsLim; i++) {
            Executor executor = new Executor(i);
            executor.start();
        }
    }

    private class Executor extends Thread {
        int index;

        Executor(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            synchronized (lock) {
                while (isActive) {
                    while (tasks.isEmpty() && isActive) {
                        try {
                            System.out.println("Поток " + index + " ожидает");
                            lock.wait();
                            System.out.println("Поток " + index + " дождался");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } if (!tasks.isEmpty()) {
                        tasks.poll().run();
                    }
                }
                System.out.println("Поток " + index + " завершил работу");
                lock.notify();
            }
        }
    }

}

