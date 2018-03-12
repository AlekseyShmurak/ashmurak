package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {

    @Test
    public void sort() {
        List<User> input = new ArrayList<>();
        input.add(new User("Vasia", 5));
        input.add(new User("Petia", 12));
        input.add(new User("Vania", 33));
        input.add(new User("Kolia", 4));
        input.add(new User("Ivan", 8));
        SortUser sort = new SortUser();
        User[] rslt = new User[input.size()];
        sort.sort(input).toArray(rslt);
        assertThat(rslt[0].getName(), is("Kolia"));
    }
}