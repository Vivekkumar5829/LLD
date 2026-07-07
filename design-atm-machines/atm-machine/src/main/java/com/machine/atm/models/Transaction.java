package com.machine.atm.models;

import com.machine.atm.enums.TransactionType;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String transactionId;
    private final Account account;
    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime timestamp;
    private final boolean isSuccess;

    public Transaction(Account account,
                       TransactionType type,
                       double amount,
                       boolean isSuccess) {
        if (account == null)
            throw new IllegalArgumentException(
                    "Account cannot be null");
        if (amount < 0)
            throw new IllegalArgumentException(
                    "Amount cannot be negative");

        this.transactionId =
                UUID.randomUUID().toString();
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = account.getBalance();
        this.timestamp = LocalDateTime.now();
        this.isSuccess = isSuccess;
    }

    // Receipt generation
    public void printReceipt() {
        System.out.println("─────────────────────");
        System.out.println("TRANSACTION RECEIPT");
        System.out.println("─────────────────────");
        System.out.println("ID: " + transactionId);
        System.out.println("Name: " +
                account.getOwner().getName());
        System.out.println("Account: " +
                account.getAccountNumber());
        System.out.println("Type: " + type);
        System.out.println("Amount: ₹" + amount);
        System.out.println("Balance: ₹" +
                balanceAfter);
        System.out.println("Time: " + timestamp);
        System.out.println("Status: " +
                (isSuccess ? "SUCCESS" : "FAILED"));
        System.out.println("─────────────────────");
    }

    public String getTransactionId() {
        return transactionId;
    }
    public Account getAccount() { return account; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() {
        return balanceAfter;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public boolean isSuccess() { return isSuccess; }
}