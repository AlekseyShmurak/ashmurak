package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    private List<Item> items = new ArrayList<>();
    private static final Random RANDOM = new Random();
    /**
     * Метод добавления заявок.
     * @return Item.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }
    /**
     * Метод редактирования заявок.
     * @param id
     * @param item
     */
    public void replace(String id, Item item) {
       this.items.set(this.findIndexById(id), item);
    }
    /**
     * Метод удаления заявки
     * @param id
     */
    public void delete(String id) {
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                this.items.remove(item);
                break;
            }
        }
    }
    /**
     * Метод возвращает все заявки
     * @return
     */
    public List<Item> findAll() {
        return this.items;
    }
    /**
     * Метод возвращает все заявки по имени
     * @param key
     * @return
     */
    public List<Item> findByName(String key) {
        List<Item> output = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                output.add(item);
            }
        }
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
        int index = this.items.size();
        for (int i = 0; i < this.items.size(); i++) {
            if (id.equals(this.items.get(i).getId())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public List<Item> getAll() {
        return this.items;
    }


}
