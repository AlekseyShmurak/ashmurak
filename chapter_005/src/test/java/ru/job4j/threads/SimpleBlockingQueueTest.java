package ru.job4j.threads;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> testQueue = new SimpleBlockingQueue<>();
    Thread producer = new Thread() {
        @Override
        public void run() {
            try {
                testQueue.offer(1);
                testQueue.offer(2);
                testQueue.offer(3);
                testQueue.offer(4);
                testQueue.offer(5);
                testQueue.offer(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Thread cunsumer = new Thread() {
        @Override
        public void run() {
            try {
                testQueue.poll();
                testQueue.poll();
                testQueue.poll();
                testQueue.poll();
                testQueue.poll();
                testQueue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    @Test
    public  void pollWaitTest() throws InterruptedException {
        System.out.println("Limit is " + testQueue.getLimit());
        producer.start();
        cunsumer.start();
        producer.join();
    }
}