package ru.job4j.coffeemachine;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {

    @Test
    public void changesTest() {
        CoffeeMachine machine = new CoffeeMachine();
        assertThat(new int[]{10, 10, 10, 10}, is(machine.changes(90, 50)));
        assertThat(new int[]{10, 10, 10, 10, 5}, is(machine.changes(90, 45)));
        assertThat(new int[]{10, 10, 10, 10, 5, 2, 1}, is(machine.changes(90, 42)));


    }
}