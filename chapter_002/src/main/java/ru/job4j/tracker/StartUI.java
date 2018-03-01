package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Input input; //Получение данных от пользователя.
    private final Tracker tracker; //Хранилище заявок.
    private int[] rage;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        rage = new int[menu.getNumberOfActions()];
        for (int i = 0; i < rage.length; i++) {
            rage[i] = i;
        }
        do {
            menu.show();
            menu.select(this.input.ask("Выберите пункт меню", rage));

        } while (!menu.toExit());
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}