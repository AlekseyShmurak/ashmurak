package ru.job4j.dom;

import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControllerTest {
    Controller cont = new Controller();


    @Test
    public void combineOrdersTest() {
        cont.addIssuer("asd");
        cont.addOrder("asd", new Order("sd", "add", "bid", 21, 20));
        cont.addOrder("asd", new Order("sd", "add", "bid", 15, 20));
        cont.addOrder("asd", new Order("sd", "add", "bid", 15, 10));
        cont.addOrder("asd", new Order("sd", "add", "bid", 12, 15));
        cont.addOrder("asd", new Order("sd", "add", "ask", 11, 10));
        cont.addOrder("asd", new Order("sd", "add", "ask", 1, 15));
        cont.addOrder("asd", new Order("sd", "add", "ask", 1, 10));
        cont.addOrder("asd", new Order("sd", "add", "ask", 12, 15));
        String rslt =  cont.showDom("asd");
        String expect =  String.format("Продажа      Цена    Покупка%n"
                                    + "    20         21           %n"
                                    + "    30         15           %n"
                                    + "               11         10%n"
                                    + "                1         25%n");
        assertThat(expect, is(rslt));
    }

    @Test
    public void showDownTest() {
        cont.addOrder("asd", new Order("sd", "add", "bid", 21, 20));
        cont.addOrder("asd", new Order("sd", "add", "bid", 20, 20));
        cont.addOrder("asd", new Order("sd", "add", "ask", 19, 20));
        cont.addOrder("asd", new Order("sd", "add", "ask", 18, 20));
        cont.showDom("asd");
        cont.addOrder("asd", new Order("sd", "add", "ask", 25, 25));
        cont.addOrder("asd", new Order("sd", "add", "ask", 25, 20));
        cont.showDom("asd");
        cont.addOrder("asd", new Order("sd", "add", "bid", 19, 20));
        String rslt =  cont.showDom("asd");
        String expect =  String.format("Продажа      Цена    Покупка%n"
                + "               19          5%n"
                + "               18         20%n");
        assertThat(expect, is(rslt));
    }
}