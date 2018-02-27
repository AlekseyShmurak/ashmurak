package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {

    private String menuStr = new StringBuilder()
            .append("0. Добавление новой заявки") .append(System.lineSeparator())
            .append("1. Вывести список всех заявок") .append(System.lineSeparator())
            .append("2. Редактирование заявки") .append(System.lineSeparator())
            .append("3. Удаление заявки.") .append(System.lineSeparator())
            .append("4. Поиск заявки по ID.") .append(System.lineSeparator())
            .append("5. Вывести список звявок по имени").append(System.lineSeparator())
            .append("6. Выход из приложения").append(System.lineSeparator())
            .toString();

    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }


    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }


    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void deleteItemTest() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("First", "asdf"));
        tracker.add(new Item("Second", "asdf"));
        Input input = new StubInput(new String[]{"3",tracker.findAll()[0].getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(1, is(tracker.findAll().length));
        assertThat("Second", is(tracker.findAll()[0].getName()));
    }

    @Test
    public void menuTest() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()),is(menuStr));
    }

    @Test
    public void showAllItemsTest() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("First", "asdf"));
        tracker.add(new Item("Second", "asdf"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()),is(menuStr +
                        new StringBuilder()
                                .append("------------ Список заявок ------------").append(System.lineSeparator())
                                .append("Имя заявки: First   Описание: asdf  ID " + tracker.getAll()[0].getId()).append(System.lineSeparator())
                                .append("Имя заявки: Second   Описание: asdf  ID " + tracker.getAll()[1].getId()).append(System.lineSeparator())
                                .append(" ").append(System.lineSeparator()).toString() +
                                menuStr));
    }

    @Test
    public void showOneNameItemsTest() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("First", "asdf"));
        tracker.add(new Item("First", "qwert"));
        Input input = new StubInput(new String[]{"5", "First", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()),is(menuStr +
                new StringBuilder()
                        .append("------------ Поиск заявок по имени ------------").append(System.lineSeparator())
                        .append("Заявки: ").append(System.lineSeparator())
                        .append("Имя заявки: First   Описание: asdf  ID " + tracker.findByName("First")[0].getId()).append(System.lineSeparator())
                        .append("Имя заявки: First   Описание: qwert  ID " + tracker.findByName("First")[1].getId()).append(System.lineSeparator())
                        .append(System.lineSeparator()).toString() +
                menuStr));
    }

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("First", "asdf"));
        Input input = new StubInput(new String[]{"4", tracker.getAll()[0].getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()),is(menuStr +
                new StringBuilder()
                        .append("------------ Поиск заявки по Id ------------").append(System.lineSeparator())
                        .append("Имя заявки: First   Описание: asdf  ID " + tracker.getAll()[0].getId()).append(System.lineSeparator())
                        .append(System.lineSeparator()).toString() +
                menuStr));
    }




}