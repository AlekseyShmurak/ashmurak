package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private  List<UserAction> actions = new ArrayList<>();
    private boolean exit = false;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions.add(new CreateItem(0, "Добавление новой заявки"));
        this.actions.add(new ShowAll(1, "Вывести список всех заявок"));
        this.actions.add(new Edit(2, "Редактирование заявки"));
        this.actions.add(new Delete(3, "Удаление заявки."));
        this.actions.add(new FindById(4, "Поиск заявки по ID."));
        this.actions.add(new FindByName(5, "Вывести список звявок по имени"));
    }

    public void select(int key) {
        if (key == 6) {
            this.exit = true;
        } else {
            this.actions.get(key).execute(this.input, this.tracker);
        }
    }

    public void show() {
        for (UserAction action: actions) {
            System.out.println(action.info());
        }
        System.out.println("6. Выход из приложения");
    }

    private void showItem(Item item) {
        System.out.println("Имя заявки: " + item.getName() + "   Описание: " + item.getDesc() + "  ID " + item.getId());
    }

    public boolean toExit() {
        return exit;
    }

    public int getNumberOfActions() {
        return this.actions.size() + 1;
    }

    private static class CreateItem extends BaseAction {

        public CreateItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой языки --------------");
            Item item = new Item(input.ask("Введите имя заявки :"), input.ask("Введите описание заявки :"));
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }
    }
    private class Edit extends BaseAction {

        public Edit(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------- Редактирование заявки -----------");
            Item editable = tracker.findById(input.ask("Введите Id редактируемой заявки"));
            editable.setName(input.ask("Введите новое имя заявки"));
            editable.setDesc(input.ask("Введите новое описание заявки"));
            tracker.replace(editable.getId(), editable);
            System.out.println("Заявка отредактированна");
            System.out.println();
        }
    }
    private class ShowAll extends BaseAction {

        public ShowAll(int key, String name) {
            super(key, name);
        }

         @Override
         public void execute(Input input, Tracker tracker) {
             System.out.println("------------ Список заявок ------------");
             if (tracker.getAll().size() == 0) {
             System.out.println("Список заявок пуст");
             } else {
                 for (Item item : tracker.getAll()) {
                 showItem(item);
                 }
             }
             System.out.println(" ");
         }
     }
    private class FindById extends BaseAction {

        public FindById(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
             System.out.println("------------ Поиск заявки по Id ------------");
             Item item = tracker.findById(input.ask("Введите Id заявки"));
             showItem(item);
             System.out.println();
        }
     }
    private class FindByName extends BaseAction {

        public FindByName(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
             System.out.println("------------ Поиск заявок по имени ------------");
             List<Item> wanted = tracker.findByName(input.ask("Введите имя заявок"));
             if (wanted.size() == 0) {
                 System.out.println("Заявки не найдены");
             } else {
                 System.out.println("Заявки: ");
                 for (Item item : wanted) {
                     showItem(item);
                 }
             }
             System.out.println();
        }
    }
}
class Delete extends BaseAction {

    public Delete(int key, String name) {
        super(key, name);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("----------- Удаление заявки -----------");
        tracker.delete(input.ask("Введите Id заявки на удаление"));
        System.out.println("Заявка удалена");
        System.out.println();
    }
}