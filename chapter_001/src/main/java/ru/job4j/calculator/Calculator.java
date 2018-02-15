package ru.job4j.calculator;

public class Calculator {

    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtract(double x, double y) {
        this.result = x - y;
    }

    public void multiply(double x, double y) {
        this.result = x * y;
    }

    public void divide(double x, double y) {
        this.result = x / y;
    }

    public double getResult() {
        return this.result;
    }
}
