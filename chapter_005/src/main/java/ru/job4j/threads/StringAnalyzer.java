package ru.job4j.threads;

public class StringAnalyzer {
    String string;
    Thread spaceThead = new Thread(new SpaceCounter());
    Thread wordsTread = new Thread(new Runnable() {
        @Override
        public void run() {
            getWordsNum(string);
        }
    } );

    public static void main(String[] args) {
        StringAnalyzer str = new StringAnalyzer("asdfdfa adfadfa adsf adf adsf adsf qewqe");
        str.spaceThead.start();
        str.wordsTread.start();
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
            }
        }
        System.out.println(spaceCount);
    }

    private void getWordsNum(String string) {
        int wordsCount = 0;
        boolean wordStart = false;
        char[] chars = string.toCharArray();
        for (char ch : chars) {
            if (ch != ' ' && !wordStart) {
                wordsCount++;
                wordStart = true;
            } else if (ch == ' ') {
                wordStart = false;
            }
        }
        System.out.println(wordsCount);
    }

    private class SpaceCounter implements Runnable {
        @Override
        public void run() {
            getSpaceNum(string);
        }
    }
}
