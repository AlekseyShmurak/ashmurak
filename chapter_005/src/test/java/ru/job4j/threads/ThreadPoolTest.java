package ru.job4j.threads;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {
    ThreadPool threadPool = new ThreadPool();

    private class TestJob implements Runnable {
        int index;
        TestJob(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("TestJob " + index + " complite");
        }
    }

    @Test
    public void test() throws InterruptedException {
        threadPool.execute();
        Thread.sleep(100);
        threadPool.addTask(new TestJob(1));
        threadPool.addTask(new TestJob(2));
        threadPool.addTask(new TestJob(3));
        threadPool.addTask(new TestJob(4));
        threadPool.addTask(new TestJob(5));
        threadPool.addTask(new TestJob(6));
        threadPool.addTask(new TestJob(7));
        threadPool.addTask(new TestJob(8));
        threadPool.addTask(new TestJob(9));
        threadPool.addTask(new TestJob(10));
        threadPool.addTask(new TestJob(11));
        threadPool.addTask(new TestJob(12));
        threadPool.addTask(new TestJob(13));
        threadPool.addTask(new TestJob(14));
        threadPool.addTask(new TestJob(15));
        threadPool.addTask(new TestJob(16));
        Thread.sleep(10000);
        threadPool.finishWorking();
        Thread.sleep(5000);
        threadPool.addTask(new TestJob(17));
        threadPool.addTask(new TestJob(18));
        threadPool.addTask(new TestJob(19));
        threadPool.addTask(new TestJob(20));
    }


}