package ru.job4j.department;

import java.util.*;

public class Department {

    public String[] sortByAscending(String[] departments) {
        Set<String> depSet = fillSet(departments, String::compareTo);
        return depSet.toArray(departments);
    }

    public String[] sortByDescending(String[] departments) {
        NavigableSet<String> depSet = fillSet(departments, descComp);
        return depSet.toArray(departments);
    }

    private TreeSet<String> fillSet(String[] departments, Comparator<String> comparator) {
        TreeSet<String> depSet = new TreeSet<>(comparator);
        for (String dep : departments) {
            char[] depAr =  dep.toCharArray();
            String add = "";
            for (int i = 0; i < depAr.length; i++) {
                if (depAr[i] == '\\') {
                    depSet.add(add);
                }
                add = add + depAr[i];
            }
            depSet.add(add);
        }
        return depSet;
    }

    private Comparator<String> descComp = new Comparator<String>() {
        @Override
        public int compare(String firstDep, String secondDep) {
            int rst = 0;
            char[] firstDepAr = firstDep.toCharArray();
            char[] secondDepAr = secondDep.toCharArray();
            int cycles = firstDepAr.length <= secondDepAr.length ? firstDepAr.length : secondDepAr.length;
            for (int i = 0; i < cycles; i++) {
                rst = Integer.compare((int) firstDepAr[i], (int) secondDepAr[i]) * -1;
                if (rst != 0) {
                    break;
                }
            }
            if (rst == 0 && firstDepAr.length != secondDepAr.length) {
                rst = Integer.compare(firstDepAr.length, secondDepAr.length);
            }
            return rst;
        }
    };




}
