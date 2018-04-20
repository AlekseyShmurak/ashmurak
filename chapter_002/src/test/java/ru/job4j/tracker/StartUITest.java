package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {

    private String menuStr = String.format("0. Добавление новой заявки%n"
            + "1. Вывести список всех заявок%n"
            + "2. Редактирование заявки%n"
            + "3. Удаление заявки.%n"
            + "4. Поиск заявки по ID.%n"
            + "5. Вывести список звявок по имени%n"
            + "6. Выход из приложения%n");


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
        tracker.deleteAllItems();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }


    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", "" + item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void deleteItemTest() {
        Tracker tracker = new Tracker();
        tracker.deleteAllItems();
        tracker.add(new Item("First", "asdf"));
        tracker.add(new Item("Second", "asdf"));
        Input input = new StubInput(new String[]{"3", "" + tracker.findAll().get(0).getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(1, is(tracker.findAll().size()));
        assertThat("Second", is(tracker.findAll().get(0).getName()));
    }

    @Test
    public void menuTest() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(menuStr));
    }

    @Test
    public void showAllItemsTest() {
        Tracker tracker = new Tracker();
        tracker.deleteAllItems();
        tracker.add(new Item("First", "asdf"));
        tracker.add(new Item("Second", "asdf"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(menuStr
                + String.format("------------ Список заявок ------------%n"
                + "Имя заявки: First   Описание: asdf  ID %s"
                + "%nИмя заявки: Second   Описание: asdf  ID %s"
                + "%n %n" + menuStr, tracker.getAll().get(0).getId(), tracker.getAll().get(1).getId())));
    }

    @Test
    public void showOneNameItemsTest() {
        Tracker tracker = new Tracker();
        tracker.deleteAllItems();
        tracker.add(new Item("First", "asdf"));
        tracker.add(new Item("First", "qwert"));
        Input input = new StubInput(new String[]{"5", "First", "6"});
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(menuStr
                + String.format("------------ Поиск заявок по имени ------------%nЗаявки: %n"
                + "Имя заявки: First   Описание: asdf  ID %s"
                + "%nИмя заявки: First   Описание: qwert  ID %s%n%n"
                + menuStr, tracker.findByName("First").get(0).getId(), tracker.findByName("First").get(1).getId())));
    }

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        tracker.deleteAllItems();
        tracker.add(new Item("First", "asdf"));
        Input input = new StubInput(new String[]{"4", "" + tracker.getAll().get(0).getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(menuStr
                + String.format("------------ Поиск заявки по Id ------------%n"
                + "Имя заявки: First   Описание: asdf  ID %s%n%n"
                + menuStr, tracker.getAll().get(0).getId())));
    }




}