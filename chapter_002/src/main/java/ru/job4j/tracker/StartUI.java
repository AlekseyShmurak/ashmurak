package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private static final String ADD = "0"; //Константа меню для добавления новой заявки.
    private static final String SHOWALL = "1"; // Константа меню для отображения всех заявок
    private static final String EDIT = "2"; // Константа меню для редактирования заявки
    private static final String DELETE = "3"; // Константа меню для удаления заявки
    private static final String ID = "4"; // Константа меню для поика заявки по Id
    private static final String NAME = "5"; // Константа меню для поика заявки по имени
    private static final String EXIT = "6"; //Константа для выхода из цикла.
    private final Input input; //Получение данных от пользователя.
    private final Tracker tracker;//Хранилище заявок.

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOWALL.equals(answer)) {
                this.showAll();
            }else if (EDIT.equals(answer)){
                this.edit();
            }else if (DELETE.equals(answer)){
                this.delete();
            }else if (ID.equals(answer)){
                this.findById();
            }else if (NAME.equals(answer)){
                this.findByName();
            }else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод отображения всех заявок
     */
    private void showAll() {
        System.out.println("------------ Список заявок ------------");
        Item[] all = this.tracker.findAll();
        if (all[0] == null){
            System.out.println("Список заявок пуст");
        }else {
            for (Item item : all) {
                showItem(item);
            }
        }
        System.out.println(" ");
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой языки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод редактирования заявки
     */
    private void edit() {
        System.out.println("----------- Редактирование заявки -----------");
        Item editable = this.tracker.findById(this.input.ask("Введите Id редактируемой заявки"));
        editable.setName(this.input.ask("Введите новое имя заявки"));
        editable.setDesc(this.input.ask("Введите новое описание заявки"));
        this.tracker.replace(editable.getId(),editable);
        System.out.println("Заявка отредактированна");
        System.out.println();
    }

    /**
     * Метод удаления заявки
     */
    private void delete() {
        System.out.println("----------- Удаление заявки -----------");
        this.tracker.delete(this.input.ask("Введите Id заявки на удаление"));
        System.out.println("Заявка удалена");
        System.out.println();
    }

    /**
     * Метод поиска заявки по Id
     */
    private void findById() {
        System.out.println("------------ Поиск заявки по Id ------------");
        Item item = this.tracker.findById(this.input.ask("Введите Id заявки"));
        showItem(item);
        System.out.println();
    }

    /**
     * Метод поиска заявок по имени
     */
    private void findByName() {
        System.out.println("------------ Поиск заявок по имени ------------");
        Item[] wanted = this.tracker.findByName(this.input.ask("Введите имя заявок"));
        if (wanted.length == 0 ) {
            System.out.println("Заявки не найдены");
        }else {
            System.out.println("Заявки: ");
            for (Item item : wanted) {
                showItem(item);
            }
        }
        System.out.println();
    }

    private void showMenu() {
        System.out.println("----------- Меню -----------");
        System.out.println("0. Добавление новой заявки");
        System.out.println("1. Вывести список всех заявок");
        System.out.println("2. Редактирование заявки");
        System.out.println("3. Удаление заявки.");
        System.out.println("4. Поиск заявки по ID.");
        System.out.println("5. Вывести список звявок по имени");
        System.out.println("6. Выход из приложения");
    }

    private void showItem (Item item) {
        System.out.println("Имя заявки: " + item.getName() + "   Описание: " + item.getDesc() + "  ID " + item.getId());
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}