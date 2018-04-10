package ru.job4j;

import java.util.Arrays;
import java.util.HashMap;

public class Anagram {

    public boolean isAnagramAr(String o1, String o2) {
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

    public boolean isAnagramHash(String o1, String o2) {
        boolean rslt = true;
        HashMap<Character, Integer> o1Table = new HashMap<>();
        for (char ch : o1.toCharArray()) {
            if (o1Table.containsKey(ch)) {
                o1Table.replace(ch, o1Table.get(ch) + 1);
            } else {
                o1Table.put(ch, 1);
            }
        }
        for (char ch : o2.toCharArray()) {
            if (!o1Table.containsKey(ch)) {
                rslt = false;
                break;
            } else {
                if (o1Table.get(ch) == 1) {
                    o1Table.remove(ch);
                } else {
                    o1Table.replace(ch, o1Table.get(ch) - 1);
                }
            }
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
