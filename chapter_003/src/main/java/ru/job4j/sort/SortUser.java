package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class SortUser {

    public Set<User> sort(List<User> users) {
        Set<User> output = new TreeSet<User>(users);
        return output;
    }

    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
       return  users;
    }

    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int rsl = o1.getName().compareTo(o2.getName());
                return rsl != 0 ? rsl : o1.compareTo(o2);
            }
        });
        return users;
    }
}
