package ru.job4j.profession;

public class Doctor extends Profession {

    Doctor(String name) {
        this.name = name;
    }

    public String build(Patient patient) {
        return this.name + " лечит " + patient.name;
    }
}
