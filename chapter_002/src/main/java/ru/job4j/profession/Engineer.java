package ru.job4j.profession;

public class Engineer extends Profession {

    Engineer(String name) {
        this.name = name;
    }

    public String build(House house) {
        return this.name + " строит " + house.project;
    }
}
