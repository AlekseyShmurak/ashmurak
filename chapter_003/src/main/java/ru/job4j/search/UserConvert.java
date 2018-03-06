package ru.job4j.search;

import java.util.HashMap;
import java.util.List;

public class UserConvert {

    public static HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> output = new HashMap<>();
        for (User value : list) {
            output.put(value.getId(),value);
        }
        return output;
    }
}
