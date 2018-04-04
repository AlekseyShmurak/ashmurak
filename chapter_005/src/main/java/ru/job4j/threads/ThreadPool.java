package ru.job4j.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private int threadsLim = Runtime.getRuntime().availableProcessors();
    private volatile boolean isActive = false;
    private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue();
    private final Object queueLock = new Object();

    public void addTask(Runnable task) {
        synchronized (queueLock) {
            this.tasks.offer(task);
            queueLock.notify();
        }
    }

    public void finishWorking() {
        System.out.println("Пул пректатил работу");
        this.isActive = false;
        synchronized (queueLock) {
            queueLock.notify();
        }
    }

    public void execute() {
        if (!isActive) {
            isActive = true;
            System.out.println("Пул запущен");
            for (int i = 0; i < threadsLim; i++) {
                Executor executor = new Executor(i);
                System.out.println("Создан поток № " + i);
                executor.start();
            }
        }
    }

    private class Executor extends Thread {
        int index;

        Executor(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            while (isActive) {
                if (tasks.isEmpty()) {
                    synchronized (queueLock) {
                        try {
                            queueLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        tasks.poll(5, TimeUnit.SECONDS).run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

