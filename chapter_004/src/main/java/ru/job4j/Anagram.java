package ru.job4j;

import java.util.Arrays;

public class Anagram {

    public boolean isAnagram(String o1, String o2) {
        boolean rslt = false;
        char[] ar1 = o1.toCharArray();
        char[] ar2 = o2.toCharArray();
        if (ar1.length == ar2.length) {
            Arrays.sort(ar1);
            Arrays.sort(ar2);
            if (Arrays.equals(ar1, ar2)) {
                rslt = true;
            }
        }
        return rslt;
    }

    public boolean isAnagramSum(String o1, String o2) {
        boolean rslt = false;
        char[] ar1 = o1.toCharArray();
        char[] ar2 = o2.toCharArray();
        if (ar1.length == ar2.length && sum(ar1) == sum(ar2)) {
            rslt = true;
        }
        return rslt;
    }

    private int sum(char[] ar) {
        int rslt = 0;
        for (char ch : ar) {
            rslt += ch * ch;
        }
        return rslt;
    }
}
