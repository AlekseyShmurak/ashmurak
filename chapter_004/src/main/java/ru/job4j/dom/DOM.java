package ru.job4j.dom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DOM { // depth of market - биржевой стакан по нерусски))
    List<Order> bitOrders = new ArrayList<>();
    List<Order> askOrders = new ArrayList<>();

    public void process(Order input) {
        if (input.getType().equals("delete")) {
            delete(input);
        } else if (input.getType().equals("add")) {
            match(input);
        }
    }

    private void delete(Order input) {
        if (input.getAction().equals("bid")) {
            delete(input, bitOrders);
        } else {
            delete(input, askOrders);
        }
    }

    private void delete(Order input, List<Order> orders) {
        for (Order order : orders) {
            if (order.getBook().equals(input.getBook()) && order.getPrice() == input.getPrice() && order.getVolume() == input.getVolume()) {
                orders.remove(order);
                break;
            }

        }
    }

    private void match(Order o1, Order o2) {
        if (o1.getVolume() > o2.getVolume()) {
            o1.setVolume(o1.getVolume() - o2.getVolume());
            o2.setVolume(0);
        } else {
            o2.setVolume(o2.getVolume() - o1.getVolume());
            o1.setVolume(0);
        }
    }

    private void match(Order input) {
        Queue<Order> ordersToDel = new LinkedList<>();
        if (input.getAction().equals("bid")) {
            for (Order order : askOrders) {
                if (order.getPrice() >= input.getPrice()) {
                    match(input, order);
                    if (order.getVolume() == 0) {
                        ordersToDel.offer(order);
                    }
                    if (input.getVolume() == 0) {
                        break;
                    }
                }
            }
            while (!ordersToDel.isEmpty()) {
                askOrders.remove(ordersToDel.poll());
            }
            if (input.getVolume() != 0) {
                bitOrders.add(input);
            }
        } else if (input.getAction().equals("ask")) {
            for (Order order : bitOrders) {
                if (order.getPrice() <= input.getPrice()) {
                    match(input, order);
                    if (order.getVolume() == 0) {
                        ordersToDel.offer(order);
                    }
                    if (input.getVolume() == 0) {
                        break;
                    }
                }
            }
            while (!ordersToDel.isEmpty()) {
                bitOrders.remove(ordersToDel.poll());
            }
            if (input.getVolume() != 0) {
                askOrders.add(input);
            }
        }

    }
}
