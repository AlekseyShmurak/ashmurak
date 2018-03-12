package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {

    @Test
    public void Test() {
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

    @Test
    public void sortNameLengthTest() {
        List<User> input = new ArrayList<>();
        input.add(new User("Vasia", 5));
        input.add(new User("P", 12));
        input.add(new User("Van", 33));
        input.add(new User("Koa", 4));
        input.add(new User("In", 8));
        SortUser sort = new SortUser();
        User[] rslt = new User[input.size()];
        sort.sortNameLength(input).toArray(rslt);
        assertThat(rslt[0].getName(), is("P"));
        assertThat(rslt[4].getName(), is("Vasia"));
    }

    @Test
    public void sortByAllFieldsTest() {
        List<User> input = new ArrayList<>();
        input.add(new User("Vasia", 5));
        input.add(new User("Pasdasd", 12));
        input.add(new User("An", 33));
        input.add(new User("Vasia", 4));
        input.add(new User("An", 8));
        SortUser sort = new SortUser();
        User[] rslt = new User[input.size()];
        sort.sortByAllFields(input).toArray(rslt);
        assertThat(rslt[0].getAge(), is(8));
        assertThat(rslt[1].getAge(), is(33));
        assertThat(rslt[2].getName(), is("Pasdasd"));
        assertThat(rslt[3].getAge(), is(4));
        assertThat(rslt[4].getAge(), is(5));

    }
}