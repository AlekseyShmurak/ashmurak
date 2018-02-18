package ru.job4j.array;

public class StringContains {

    public boolean contains(String origin, String sub) {
        char[] originAr = origin.toCharArray();
        char[] subAr = sub.toCharArray();
        boolean result = false;
        for (int i=0; i < originAr.length - subAr.length; i++){
            boolean stepRstl = true;
            for (int j=0; j < subAr.length;j++) {
                if (originAr[i+j] != subAr[j]) {
                    stepRstl = false;
                    j = subAr.length;
                }
            }
            if (stepRstl){
                result = true;
            }
        }return result;
    }
}
