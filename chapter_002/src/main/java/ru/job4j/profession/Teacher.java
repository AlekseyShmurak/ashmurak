package ru.job4j.profession;

public class Teacher extends Profession {

    Teacher (String name) {
        this.name = name;
    }

    public String  teach(Student student) {
        return this.name + " учит " + student.name;

    }


}
