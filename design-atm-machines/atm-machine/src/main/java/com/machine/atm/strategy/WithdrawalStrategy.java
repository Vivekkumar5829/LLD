package com.machine.atm.strategy;

import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Account;
import com.machine.atm.models.Transaction;

public class WithdrawalStrategy
        implements TransactionStrategy {

    @Override
    public void execute(ATM atm, double amount) {
        Account account = atm.getCurrentAccount();

        // Validate amount
        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");

        // Check account balance
        if (!account.hasSufficientBalance(amount))
            throw new IllegalStateException(
                    "Insufficient account balance. " +
                            "Available: ₹" +
                            account.getBalance());

        // Check ATM cash
        if (!atm.getCashDispenser()
                .hasSufficientCash(amount))
            throw new IllegalStateException(
                    "ATM has insufficient cash.");

        // Debit account
        account.debit(amount);

        // Dispense cash
        atm.getCashDispenser()
                .dispenseCash(amount);

        // Record transaction
        Transaction tx = new Transaction(
                account,
                TransactionType.WITHDRAWAL,
                amount, true);

        // Print receipt
        tx.printReceipt();
    }
}