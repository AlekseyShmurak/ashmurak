package ru.job4j.comparator;
import java.util.Comparator;
import java.util.List;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rst = 0;
        char[] leftAr = left.toCharArray();
        char[] rightAr = right.toCharArray();
        int cycles = leftAr.length <= rightAr.length ? leftAr.length : rightAr.length;
        for (int i = 0; i < cycles; i++ ) {
            rst = Integer.compare((int) leftAr[i], (int) rightAr[i]);
            if (rst != 0) {
                break;
            }
        }
        if (rst == 0 && leftAr.length != rightAr.length ) {
            rst = Integer.compare(leftAr.length, rightAr.length);
        }
        return rst;
    }


}
