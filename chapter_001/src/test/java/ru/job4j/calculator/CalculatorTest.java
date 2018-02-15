package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void sutractTest() {
        Calculator testCalc = new Calculator();
        testCalc.subtract(5.0, 3.0);
        double testResult = testCalc.getResult();
        double expected = 2.0;
        assertThat(testResult, is(expected));
    }

    @Test
    public void multiplyTest() {
        Calculator testCalc = new Calculator();
        testCalc.multiply(5.0, 3.0);
        double testResult = testCalc.getResult();
        double expected = 15.0;
        assertThat(testResult, is(expected));
    }

    @Test
    public void divideTest() {
        Calculator testCalc = new Calculator();
        testCalc.divide(15.0, 3.0);
        double testResult = testCalc.getResult();
        double expected = 5.0;
        assertThat(testResult, is(expected));
    }
}
