package ru.job4j.threads;

public class UnsynthProblem {
    int rslt = 0;
    Thread addTwoTenTimes = new Thread(new Runnable() {
        @Override
        public void run() {
            int temp = rslt;
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                temp += 2;
            }
            rslt = temp;
        }
    });

    Thread addFiveTenTimes = new Thread(new Runnable() {
        @Override
        public void run() {
            int temp = rslt;
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                temp += 5;
            }
            rslt = temp;
        }
    });

    public static void main(String[] args) {
        UnsynthProblem test = new UnsynthProblem();
        test.addFiveTenTimes.start();
        test.addTwoTenTimes.start();
        try {
            test.addTwoTenTimes.join();
            test.addFiveTenTimes.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Result " + test.rslt);
    }
}
