package ru.job4j.bank;

import java.util.*;

public class BankController {

    private Map<User, List<Account>> repository = new HashMap<User, List<Account>>();

    public void addUser(User user) {
        this.repository.putIfAbsent(user, new ArrayList<Account>());
    }

    public void deleteUser(User user) {
        this.repository.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        this.repository.get(this.getUser(passport)).add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = this.getUser(passport);
        this.repository.get(user).remove(this.repository.get(user).indexOf(account));

    }

    public List<Account> getUserAccounts(String passport) {
        return this.repository.get(this.getUser(passport));
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        return getUserAccounts(srcPassport).get(getUserAccounts(srcPassport).indexOf(new Account(0.0, srcRequisite))).transferMoney(
                getUserAccounts(destPassport).get(getUserAccounts(destPassport).indexOf(new Account(0.0, dstRequisite))), amount);
    }

    public User getUser(String passport) {
        User rslt = new User();
        for (User user : this.repository.keySet()) {
            if (passport.equals(user.getPassport())) {
                rslt = user;
            }
        }
        return rslt;
    }

    public Set<User> getAllUsers() {
        return this.repository.keySet();
    }
}
