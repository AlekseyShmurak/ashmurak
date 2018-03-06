package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class UserConvertTest {

    @Test
    public void processTest() {
        List<User> input = new ArrayList<>();
        input.add(new User(1, "asdsd", "adffda"));
        input.add(new User(2, "qweqe", "uiooiu"));
        input.add(new User(3, "kjhkjh", "kjhkj"));
        HashMap<Integer, User> usersMap = UserConvert.process(input);
        assertThat("qweqe", is(usersMap.get(2).getName()));
        assertThat("kjhkjh", is(usersMap.get(3).getName()));
    }
}