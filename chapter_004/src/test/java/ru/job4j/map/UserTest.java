package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.*;

import static org.junit.Assert.*;

public class UserTest {

    Calendar date = new GregorianCalendar();

    Map<User, String> map = new HashMap();
    User first = new User("Vasia", 2, date);
    User second = new User("Vasia", 2, date);

    @Test
    public void mapTest() {
        map.put(first, "asd");
        map.put(second, "qwe");
        Iterator<User> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().name);
        }
        assertThat(1, is(map.keySet().size()));
    }
}