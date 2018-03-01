package ru.job4j.tracker;

public class ValidateInput implements Input {
    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, int[] rage) {
        boolean invalid = true;
        int value = - 1;
        do{
            try {
                value = this.input.ask(question, rage);
                invalid = false;
            } catch ( NumberFormatException error) {
                System.out.println("Введены неверные данные");
            } catch ( MenuOutException moe) {
                System.out.println("Введен не существующий пункт меню");
            }
        } while (invalid);
        return value;
    }
}
