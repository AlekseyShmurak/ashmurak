package ru.job4j.threads;

public class InterruptThread {
    int timeLimit = 5;
    int charCount = 0;
    String string;
    Thread timeThread = new Thread(new Time());
    Thread charThread = new Thread(new CountChar());

    public InterruptThread(String string) {
        this.string = string;
    }

    public static void main(String[] args) {
        InterruptThread mainThr = new InterruptThread("tqeuirytqeuytqeriuytqriuqtrytquriturtqrtuqitruqtruqtrutrutrutrutrut");
        mainThr.charThread.start();
        mainThr.timeThread.start();
        try {
            mainThr.charThread.join();
            mainThr.timeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final chars " + mainThr.charCount);
    }

    private class Time implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i <= timeLimit; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Time " + i);
                } catch (InterruptedException e) {
                    System.out.println("Time " + i);
                }
            }
            timeThread.interrupt();
        }
    }

    private class CountChar implements Runnable {
        @Override
        public void run() {
            char[] chars = string.toCharArray();
            for (char ch : chars) {
                if (timeThread.isInterrupted() || !timeThread.isAlive()) {
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("ch " + charCount);
                charCount++;
            }
        }
    }
}
