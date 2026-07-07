package com.machine.atm.models;

import com.machine.atm.enums.AccountType;
import java.util.UUID;

public class Account {
    private final String accountId;
    private final String accountNumber;
    private final User owner;
    private double balance;
    private final AccountType accountType;
    private boolean isActive;

    public Account(String accountNumber,
                   User owner,
                   double initialBalance,
                   AccountType accountType) {
        if (accountNumber == null ||
                accountNumber.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Account number cannot be empty");
        if (owner == null)
            throw new IllegalArgumentException(
                    "Owner cannot be null");
        if (initialBalance < 0)
            throw new IllegalArgumentException(
                    "Balance cannot be negative");

        this.accountId = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.isActive = true;
    }

    // Withdraw money
    public void debit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        if (amount > balance)
            throw new IllegalStateException(
                    "Insufficient balance");
        this.balance -= amount;
    }

    // Deposit money
    public void credit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.balance += amount;
    }

    public boolean hasSufficientBalance(
            double amount) {
        return balance >= amount;
    }

    public String getAccountId() {
        return accountId;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public User getOwner() { return owner; }
    public double getBalance() { return balance; }
    public AccountType getAccountType() {
        return accountType;
    }
    public boolean isActive() { return isActive; }
    public void deactivate() {
        this.isActive = false;
    }
}