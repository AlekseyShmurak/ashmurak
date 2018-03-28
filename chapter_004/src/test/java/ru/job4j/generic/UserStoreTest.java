package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class UserStoreTest {

    UserStore userStore = new UserStore(5);
    User test = new User("qwerty");


    @Before
    public void setup() {
        userStore.add(new User("1234"));
        userStore.add(new User("4321"));
    }

    @Test
    public void findByIdTest() {
        assertThat(userStore.findById("1234").getId(), is("1234"));
        assertThat(userStore.findById("1234").getClass().toString(), is("class ru.job4j.generic.User"));
    }

    @Test
    public void replaceTest() {
        userStore.replace("1234", test);
        assertThat(userStore.findById("qwerty").getId(), is("qwerty"));
    }

    @Test
    public void deleteTest() {
        assertThat(userStore.delete("1234"), is(true));
        assertThat(userStore.delete("12"), is(false));
    }

    @Test
    public void transferTwoTreadsTest() {
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                userStore.add(new User("qewrr",  320));
                userStore.add(new User("rr",  100));
            }
        });
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                userStore.transfer("qewrr", "rr", 100);
                assertThat(200, is(userStore.findById("qewrr").getAmount()));
            }
        });
        first.start();
        second.start();

    }

}