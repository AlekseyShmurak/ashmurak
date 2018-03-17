package ru.job4j.department;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Department {

    public String[] sortByAscending(String[] departments) {
        List<String> depList = new ArrayList<>();
        for (String dep : departments) {
            depList.add(dep);
        }
        depList.sort(new Comparator<String>() {
            @Override
            public int compare(String firstDep, String secondDep) {
                int rst = 0;
                char[] firstDepAr = firstDep.toCharArray();
                char[] secondDepAr = secondDep.toCharArray();
                int cycles = firstDepAr.length <= secondDepAr.length ? firstDepAr.length : secondDepAr.length;
                for (int i = 0; i < cycles; i++) {
                    rst = Integer.compare((int) firstDepAr[i], (int) secondDepAr[i]);
                    if (rst != 0) {
                        break;
                    }
                }
                if (rst == 0 && firstDepAr.length != secondDepAr.length) {
                    rst = Integer.compare(firstDepAr.length, secondDepAr.length);
                }
                return rst;
            }
        });
        return depList.toArray(departments);
    }

    public String[] sortByDescending(String[] departments) {
        List<String> depList = new ArrayList<>();
        for (String dep : departments) {
            depList.add(dep);
        }
        depList.sort(new Comparator<String>() {
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
        });
        return depList.toArray(departments);
    }




}
