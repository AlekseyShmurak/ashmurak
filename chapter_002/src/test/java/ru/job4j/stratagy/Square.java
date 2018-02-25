package ru.job4j.stratagy;

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("00000");
        pic.append("00000");
        pic.append("00000");
        pic.append("00000");
        return pic.toString();
    }
}
