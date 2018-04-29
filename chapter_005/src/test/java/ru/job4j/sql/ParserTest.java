package ru.job4j.sql;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParserTest {

    @Test
    public void initAndGetSumTest() {
        Parser parser = new Parser();
        parser.init(1000000);
        int rslt = parser.getSum();
        assertThat(rslt, is(1784293664));
    }

}