package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleHashTableTest {
    SimpleHashTable<Integer, String> map = new SimpleHashTable();
    SimpleHashTable<User, String> mapUser = new SimpleHashTable();
    Calendar date = new GregorianCalendar();
    User userOne = new User("vasia", 2, date);
    User userTwoSameOne = new User("vasia", 2, date);
    User user3 = new User("vasia", 3, date);

    @Test
    public void insertAndGetTest() {
        map.insert(5, "qwer");
        String str = map.get(5);
        assertThat("qwer", is(map.get(5)));
        mapUser.insert(userOne, "zxcv");
        mapUser.insert(userTwoSameOne, "asdf");
        mapUser.insert(user3, "rewq");
        assertThat("zxcv", is(mapUser.get(userOne)));
        assertThat("rewq", is(mapUser.get(user3)));
        assertThat(false, is(mapUser.insert(user3, "rewq")));
    }

    @Test
    public void enlargeContTest() {
        map.insert(1, "qwe");
        map.insert(2, "qwe");
        map.insert(3, "qwe");
        map.insert(4, "qwe");
        map.insert(5, "qwe");
        map.insert(6, "qwe");
        map.insert(7, "qwe");
        map.insert(8, "qwe");
        map.insert(9, "qwe");
        map.insert(10, "qwe");
        map.insert(11, "qwe");
        map.insert(12, "qwe");
        map.insert(13, "qwe");
        map.insert(14, "qwe");
        map.insert(15, "qwe");
        map.insert(16, "qwe");
        map.insert(36, "qwe");
        map.insert(26, "qwe");
        assertThat(18, is(map.getSize()));
    }

    @Test
    public void deleteTest() {
        User firstUser = new User("Sasha", 8, date);
        User secondUser = new User("Petia", 8, date);
        mapUser.insert(firstUser, "asdf");
        mapUser.insert(secondUser, "asdf");
        mapUser.insert(userOne, "zxcv");
        mapUser.insert(user3, "rewq");
        mapUser.delete(userOne);
        assertThat(null, is(mapUser.get(userOne)));
        assertThat("rewq", is(mapUser.get(user3)));
        assertThat("asdf", is(mapUser.get(firstUser)));
        assertThat("asdf", is(mapUser.get(secondUser)));
    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void iteratorTest() {
        map.insert(1, "qwe");
        map.insert(2, "qwe");
        map.insert(3, "qwe");
        Iterator<Integer> intIter = map.getKeyIterator();
        assertThat(true, is(intIter.hasNext()));
        assertThat(1, is(intIter.next()));
        assertThat(true, is(intIter.hasNext()));
        assertThat(2, is(intIter.next()));
        assertThat(true, is(intIter.hasNext()));
        assertThat(3, is(intIter.next()));
        assertThat(false, is(intIter.hasNext()));
        mapUser.insert(userOne, "zxcv");
        mapUser.insert(user3, "rewq");
        Iterator<User> userIterator = mapUser.getKeyIterator();
        assertThat(true, is(userIterator.hasNext()));
        assertThat(userOne, is(userIterator.next()));
        assertThat(true, is(userIterator.hasNext()));
        assertThat(user3, is(userIterator.next()));
        assertThat(false, is(userIterator.hasNext()));
        map.delete(3);
        intIter.hasNext();
    }

}