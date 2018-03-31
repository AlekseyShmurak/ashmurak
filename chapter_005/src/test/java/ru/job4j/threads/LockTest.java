package ru.job4j.threads;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LockTest {
    Thread first = new Thread(new TestR(100000));
    Thread second = new Thread(new TestR(200000));
    private Lock lock = new Lock();
    int counter = 0;

    private class TestR implements Runnable {
        int expectVal;

        public TestR(int expectVal) {
            this.expectVal = expectVal;
        }

        @Override
        public void run() {
            lock.lock();
            while (counter < expectVal) {
                counter++;
            }
            System.out.println("Результат " + counter);
            lock.unlock();
        }
    }

    @Test
    public void test() throws InterruptedException {
        first.start();
        second.start();
        second.join();
    }

}