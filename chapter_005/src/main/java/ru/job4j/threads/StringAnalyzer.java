package ru.job4j.threads;

public class StringAnalyzer {
    String string;
    Thread spaceThead = new Thread(new SpaceCounter());
    Thread wordsTread = new Thread(new Runnable() {
        @Override
        public void run() {
            getWordsNum(string);
        }
    });



    public static void main(String[] args) {
        StringAnalyzer str = new StringAnalyzer("asdfdfa adfadfa adsf adf adsf adsf qewqe asdfdfa adfadfa adsf adf adsf adsf qewqe");
        str.startProg();

    }

    public void startProg() {
        System.out.println("Start");
        spaceThead.start();
        wordsTread.start();
        try {
            spaceThead.join();
            wordsTread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish");
    }

    public StringAnalyzer(String string) {
        this.string = string;
    }

    private void getSpaceNum(String string) {
        int spaceCount = 0;
        char[] chars = string.toCharArray();
        for (char ch : chars) {
            if (ch == ' ') {
                spaceCount++;
                System.out.println("Spaces " + spaceCount);
            }
        }
    }

    private void getWordsNum(String string) {
        int wordsCount = 0;
        boolean wordStart = false;
        char[] chars = string.toCharArray();
        for (char ch : chars) {
            if (ch != ' ' && !wordStart) {
                wordsCount++;
                wordStart = true;
                System.out.println("Words " + wordsCount);
            } else if (ch == ' ') {
                wordStart = false;
            }
        }
    }

    private class SpaceCounter implements Runnable {
        @Override
        public void run() {
            getSpaceNum(string);
        }
    }
}
