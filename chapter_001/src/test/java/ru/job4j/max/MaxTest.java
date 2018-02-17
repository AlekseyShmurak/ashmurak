package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void getMaxNumberTest() {
        Max max = new Max();
        assertThat(5,is(max.getMaxNumber(5,3)));
        assertThat(5,is(max.getMaxNumber(3,5)));
        assertThat(5,is(max.getMaxNumber(5,5)));
    }


}