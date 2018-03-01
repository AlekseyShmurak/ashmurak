package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {

    public int ask( String question, int[] rage) {
        boolean invalid = true;
        int value = - 1;
        do{
            try {
                value = super.ask(question, rage);
                invalid = false;
            } catch ( NumberFormatException error) {
                System.out.println("Введены не верные данные");
            } catch ( MenuOutException moe) {
                System.out.println("Введен не существующий пункт меню");
            }
        } while (invalid);
        return value;
    }
}
