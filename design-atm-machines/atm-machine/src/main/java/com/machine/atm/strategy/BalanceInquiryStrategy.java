package com.machine.atm.strategy;

import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Account;
import com.machine.atm.models.Transaction;

public class BalanceInquiryStrategy
        implements TransactionStrategy {

    @Override
    public void execute(ATM atm, double amount) {
        Account account = atm.getCurrentAccount();

        System.out.println(
                "Account: " +
                        account.getAccountNumber());
        System.out.println(
                "Available Balance: ₹" +
                        account.getBalance());

        // Record transaction
        Transaction tx = new Transaction(
                account,
                TransactionType.BALANCE_INQUIRY,
                0, true);

        tx.printReceipt();
    }
}