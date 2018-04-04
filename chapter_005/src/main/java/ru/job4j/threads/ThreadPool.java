package ru.job4j.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private int threadsLim = Runtime.getRuntime().availableProcessors();
    private boolean isActive = false;
    private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue();

    public void addTask(Runnable task) {
        this.tasks.offer(task);
    }

    public void finishWorking() {
        System.out.println("Пул пректатил работу");
        this.isActive = false;
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
                try {
                    tasks.poll(5, TimeUnit.SECONDS).run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    isActive = false;
                }
            }
        }
    }

}

