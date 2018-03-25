package ru.job4j.dom;

import java.util.HashMap;
import java.util.List;

public class Controller {
    private HashMap<String, DOM> repository = new HashMap<>();

    public void addIssuer(String issuer) {
        this.repository.put(issuer, new DOM());
    }

    public void addOrder(String issuer, Order order) {
        if (!repository.containsKey(issuer)) {
            addIssuer(issuer);
        }
        repository.get(issuer).process(order);
    }

    public String showDom(String issuer) {
        List<Order> bit = repository.get(issuer).bitOrders;
        bit.sort(Order.priseComp());
        List<Order> ask = repository.get(issuer).askOrders;
        ask.sort(Order.priseComp());
        int tempVal = 0;
        System.out.println("Продажа      Цена    Покупка");
        String output = String.format("Продажа      Цена    Покупка%n");
        for (int i = 0; i < bit.size(); i++) {
            if (i + 1 < bit.size() && bit.get(i).getPrice() == bit.get(i + 1).getPrice()) {
                tempVal += bit.get(i).getVolume();
            } else {
                tempVal += bit.get(i).getVolume();
                System.out.printf("%6d %10d           %n", tempVal, bit.get(i).getPrice());
                output = String.format(output + "%6d %10d           %n", tempVal, bit.get(i).getPrice());
                tempVal = 0;
            }
        }
        for (int i = 0; i < ask.size(); i++) {
            if (i + 1 < ask.size() && ask.get(i).getPrice() == ask.get(i + 1).getPrice()) {
                tempVal += ask.get(i).getVolume();
            } else {
                tempVal += ask.get(i).getVolume();
                System.out.printf("       %10d %10d%n", ask.get(i).getPrice(), tempVal);
                output = String.format(output + "       %10d %10d%n", ask.get(i).getPrice(), tempVal);
                tempVal = 0;
            }
        }
        return output;
    }
}
