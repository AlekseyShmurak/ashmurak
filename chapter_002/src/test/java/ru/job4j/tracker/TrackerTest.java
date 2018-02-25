package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void addTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription","111");
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    @Test
    public void replaceTest() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1","testDescription","111");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2","testDescription2","111");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void deleteTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription","111");
        tracker.add(item);
        tracker.delete(tracker.getAll()[0].getId());
        assertThat(tracker.getAll()[0], is(tracker.getAll()[99]));
    }

    @Test
    public void findAllTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription","111");
        tracker.add(item);
        Item item2 = new Item("test1","testDescription","111");
        tracker.add(item2);
        assertThat(tracker.findAll().length,is(2));
    }

    @Test
    public void findByNameTest() {Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription","111");
        tracker.add(item);
        Item item2 = new Item("test1","testDescription","111");
        tracker.add(item2);
        Item item3 = new Item("test2","testDescription","111");
        tracker.add(item3);
        assertThat(tracker.findByName("test1").length,is(2));
        assertThat(tracker.findAll().length,is(3));
        assertThat(tracker.findByName("test1")[0].getName(),is("test1"));
    }

    @Test
    public void findByNameTestEmpty() {Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription","111");
        tracker.add(item);
        Item item2 = new Item("test1","testDescription","111");
        tracker.add(item2);
        Item item3 = new Item("test2","testDescription","111");
        tracker.add(item3);
        assertThat(tracker.findByName("test3").length,is(0));
    }
}