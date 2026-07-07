package com.machine.atm.strategy;

import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Account;
import com.machine.atm.models.Transaction;

public class DepositStrategy
        implements TransactionStrategy {

    @Override
    public void execute(ATM atm, double amount) {
        Account account = atm.getCurrentAccount();

        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Deposit amount must be positive");

        // Credit account
        account.credit(amount);

        // Record transaction
        Transaction tx = new Transaction(
                account,
                TransactionType.DEPOSIT,
                amount, true);

        // Print receipt
        tx.printReceipt();

        System.out.println(
                "₹" + amount + " deposited. " +
                        "New balance: ₹" +
                        account.getBalance());
    }
}