package ru.job4j.dom;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DOMTest {
    DOM dom = new DOM();

    @Test
    public void deleteTest() {
        dom.process(new Order("sd", "add", "bid", 21, 20));
        dom.process(new Order("sd", "add", "bid", 20, 20));
        dom.process(new Order("sd", "add", "ask", 19, 20));
        dom.process(new Order("sd", "add", "ask", 18, 20));
        assertThat(2, is(dom.askOrders.size()));
        assertThat(2, is(dom.bitOrders.size()));
        dom.process(new Order("sd", "delete", "bid", 21, 20));
        dom.process(new Order("sd", "delete", "ask", 19, 20));
        assertThat(1, is(dom.askOrders.size()));
        assertThat(1, is(dom.bitOrders.size()));

    }

    @Test
    public void matchTest() {
        dom.process(new Order("sd", "add", "bid", 21, 20));
        dom.process(new Order("sd", "add", "bid", 20, 20));
        dom.process(new Order("sd", "add", "ask", 19, 20));
        dom.process(new Order("sd", "add", "ask", 18, 20));
        assertThat(2, is(dom.askOrders.size()));
        assertThat(2, is(dom.bitOrders.size()));
        dom.process(new Order("sd", "add", "ask", 25, 25));
        dom.process(new Order("sd", "add", "ask", 25, 20));
        assertThat(0, is(dom.bitOrders.size()));
        assertThat(3, is(dom.askOrders.size()));
        assertThat(5, is(dom.askOrders.get(2).getVolume()));
        dom.process(new Order("sd", "add", "bid", 19, 20));
        assertThat(2, is(dom.askOrders.size()));
    }
}