package ru.job4j.stratagy;

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("0   ");
        pic.append("00  ");
        pic.append("000 ");
        pic.append("0000");
        return  pic.toString();
    }
}
