package ru.job4j.bank;

import org.junit.Test;
import java.util.Arrays;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankControllerTest {

    @Test
    public void addUserTest() {
        BankController bankController = new BankController();
        bankController.addUser(new User("Vasia", "ASDF"));
        assertThat("Vasia", is(bankController.getUser("ASDF").getName()));
    }

    @Test
    public void deleteUserTest() {
        BankController bankController = new BankController();
        bankController.addUser(new User("Vasia", "ASDF"));
        bankController.deleteUser(bankController.getUser("ASDF"));
        assertThat(0, is(bankController.getAllUsers().size()));
    }

    @Test
    public void addAccountToUserTest() {
        BankController bankController = new BankController();
        bankController.addUser(new User("Vasia", "ASDF"));
        bankController.addAccountToUser("ASDF", new Account(5000.0, "555555"));
        assertThat("555555", is(bankController.getUserAccounts("ASDF").get(0).getRequisites()));
    }

    @Test
    public void deleteAccountFromUserTest() {
        BankController bankController = new BankController();
        bankController.addUser(new User("Vasia", "ASDF"));
        bankController.addAccountToUser("ASDF", new Account(5000.0, "555555"));
        bankController.deleteAccountFromUser("ASDF", new Account(5000.0, "555555"));
        assertThat(0, is(bankController.getUserAccounts("ASDF").size()));
    }

    @Test
    public void getUserAccountsTest() {
        BankController bankController = new BankController();
        bankController.addUser(new User("Vasia", "ASDF"));
        bankController.addAccountToUser("ASDF", new Account(5000.0, "555555"));
        bankController.addAccountToUser("ASDF", new Account(2500.0, "333333"));
        assertThat(2, is(bankController.getUserAccounts("ASDF").size()));
    }

    @Test
    public void transferMoneyTest() {
        BankController bankController = new BankController();
        bankController.addUser(new User("Vasia", "ASDF"));
        bankController.addUser(new User("Petia", "ZXCV"));
        bankController.addAccountToUser("ASDF", new Account(5000.0, "555555"));
        bankController.addAccountToUser("ZXCV", new Account(2500.0, "333333"));
        bankController.transferMoney("ASDF", "555555", "ZXCV", "333333", 1000);
        assertThat(4000.0, is(bankController.getUserAccounts("ASDF").get(0).getValue()));
        assertThat(3500.0, is(bankController.getUserAccounts("ZXCV").get(0).getValue()));

    }
}