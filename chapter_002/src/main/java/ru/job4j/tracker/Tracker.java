package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.util.Random;


/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;
    private static final Random RANDOM = new Random();
    /**
     * Метод добавления заявок.
     * @return Item.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }
    /**
     * Метод редактирования заявок.
     * @param id
     * @param item
     */
    public void replace(String id, Item item) {
       this.items[findIndexById(id)] = item;
    }
    /**
     * Метод удаления заявки
     * @param id
     */
    public void delete(String id) {
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                this.items[items.length - 1] = null;
                this.position--;
                break;
            }
        }
    }
    /**
     * Метод возвращает все заявки
     * @return
     */
    public Item[] findAll() {
        Item[] output = this.items;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                output = new Item[i];
                System.arraycopy(this.items, 0, output, 0, output.length);
                break;
            }
        }
        return output;
    }
    /**
     * Метод возвращает все заявки по имени
     * @param key
     * @return
     */
    public Item[] findByName(String key) {
        Item[] temp = new Item[this.items.length];
        int position = 0;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                break;
            }
            if (this.items[i].getName().equals(key)) {
                temp[position] = this.items[i];
                position++;
            }
        }
        Item[] output = new Item[position];
        System.arraycopy(temp, 0, output, 0, output.length);
        return output;
    }
    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(RANDOM.nextInt(1000000)) + LocalDateTime.now();
    }
    /**
     * Метод находит Item по id
     * @param id
     * @return Item
     */
    public Item findById(String id) {
        Item output = new Item();
        for (Item item : items) {
                if (id.equals(item.getId())) {
                output = item;
                break;
            }
        }
        return output;
    }

    private int findIndexById(String id) {
        int index = this.items.length;
        for (int i = 0; i < this.items.length; i++) {
            if (id.equals(this.items[i].getId())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Item[] getAll() {
        return this.items;
    }


}
