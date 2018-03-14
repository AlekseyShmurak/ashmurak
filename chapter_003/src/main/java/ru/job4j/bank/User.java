package ru.job4j.bank;

public class User {
    private String name;
    private String passport;

    public User() {
    }

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object object) {
        boolean rstl = false;
        if (object instanceof User) {
            User user = (User) object;
            rstl = this.passport == user.passport ? true : false;
        }
        return rstl;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int result = 1;
        result = prime * result + this.passport.hashCode();
        return result;
    }

    public String getPassport() {
        return this.passport;
    }

    public String getName() {
        return name;
    }
}
