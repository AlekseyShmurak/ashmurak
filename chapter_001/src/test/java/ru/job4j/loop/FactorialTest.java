package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {

    @Test
    public void calcTest() {
        Factorial fact = new Factorial();
        assertThat(120, is(fact.calc(5)));
    }

}