package ru.job4j.bank;

public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.requisites = requisites;
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public boolean transferMoney(Account dest, double amount) {
        boolean rst = false;
        if (amount <= this.value) {
            this.value -= amount;
            dest.value += amount;
            rst = true;
        }
        return rst;
    }

    @Override
    public boolean equals(Object object) {
        boolean rslt = false;
        if (object instanceof Account) {
            Account account = (Account) object;
            rslt =  this.requisites == account.requisites ? true : false;
        }
        return rslt;
    }

    @Override
    public int hashCode() {
        final int prime = 27;
        int result = 1;
        result = prime * result + this.requisites.hashCode();
        return result;
    }
}
