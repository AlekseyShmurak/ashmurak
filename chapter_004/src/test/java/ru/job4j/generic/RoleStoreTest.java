package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoleStoreTest {

    RoleStore roleStore = new RoleStore(5);
    Role test = new Role("qwerty");



    @Before
    public void setup() {
        roleStore.add(new Role("1234"));
        roleStore.add(new Role("4321"));
    }

    @Test
    public void findByIdTest() {
        assertThat(roleStore.findById("1234").getId(), is("1234"));
        assertThat(roleStore.findById("1234").getClass().toString(), is("class ru.job4j.generic.Role"));
    }

    @Test
    public void replaceTest() {
        roleStore.replace("1234", test);
        assertThat(roleStore.findById("qwerty").getId(), is("qwerty"));
    }

    @Test
    public void deleteTest() {
        assertThat(roleStore.delete("1234"), is(true));
        assertThat(roleStore.delete("12"), is(false));
    }

}