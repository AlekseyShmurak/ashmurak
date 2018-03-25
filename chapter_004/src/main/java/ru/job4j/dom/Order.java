package ru.job4j.dom;

import java.util.Comparator;
import java.util.Random;

public class Order {
    static Random rnd = new Random();
    private int id;
    private String book;
    private String type; //  add/delete
    private String action; // bid/ask
    private int price;
    private int volume;
    static public Comparator<Order> priseComp() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int rslt = 0;
                if (o1.getPrice() > o2.getPrice()) {
                    rslt = -1;
                } else if (o1.getPrice() < o2.getPrice()) {
                    rslt = 1;
                }
                return rslt;
            }
        };
    }

    public Order(String book, String type, String action, int price, int volume) {
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
        this.id = rnd.nextInt(10);
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public int getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
