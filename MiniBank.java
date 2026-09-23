import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import util.WithdrawRule;
import util.AnnotationValidator;

public class MiniBank {

    public static void main(String[] args) {

        Account[] accounts = {
            new SavingsAccount(101, "Rudra", 10000, 2000),
            new CurrentAccount(102, "Rahul", 5000, 2000),
            new FixedDepositAccount(103, "Amit", 20000)
        };

        // Polymorphism

        for (Account account : accounts) {

            System.out.println(
                account.getOwnerName() +
                " : " +
                account.interestRate()
            );

            if (account instanceof SavingsAccount) {
                System.out.println("This is a Savings Account");
            }
        }

        // Anonymous class

        WithdrawRule rule1 = new WithdrawRule() {

            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        System.out.println(
            rule1.allow(accounts[0], 1000)
        );

        WithdrawRule rule2 =
                (account, amount) -> account.canWithdraw(amount);

        System.out.println(
            rule2.allow(accounts[1], 1000)
        );

        // Annotation testing

        Account testAccount =
                new SavingsAccount(104, "Test", -5000, 1000);

        String[] errors =
                AnnotationValidator.validate(testAccount);

        for (String error : errors) {
            System.out.println(error);
        }
    }
}