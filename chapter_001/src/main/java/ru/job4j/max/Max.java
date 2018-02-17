package ru.job4j.max;


public class Max {

    public int getMaxNumber(int first, int second ) {
                 return  first > second ? first : second;
    }

    public int getMaxNumber(int first, int second, int third) {
        return getMaxNumber(getMaxNumber(first,second),third);
    }
}
