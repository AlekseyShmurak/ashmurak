package ru.job4j.coffeemachine;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {

    public int[] changes(int value, int price) {
        List<Integer> outPutList = new ArrayList<>();
        int[] coins = {10, 5, 2, 1};
        value -= price;
        int coinsIndex = 0;
        while (value != 0) {
            if (value / coins[coinsIndex] >= 1) {
                int numberOfCoins = value / coins[coinsIndex];
                for (int i = 0; i < numberOfCoins; i++) {
                    outPutList.add(coins[coinsIndex]);
                }
                value = value - numberOfCoins * coins[coinsIndex];
            }
            coinsIndex++;
        }
        int[] output = new int[outPutList.size()];
        for (int i = 0; i <output.length; i++) {
            output[i] = outPutList.get(i);
        }
        return output;
    }
}
