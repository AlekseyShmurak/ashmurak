package ru.job4j.array;

public class Square {
    public int[] calculate (int bound) {
        int[] rst = new int[bound];
        int counter = 0;
        for (int i = 0; i < bound; i++) {
            rst[i] = (i+1)*(i+1);
        }
        return rst;
    }
}
