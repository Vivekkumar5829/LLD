package com.machine.atm.models;

public class CashDispenser {
    private double totalCash;

    public CashDispenser(double totalCash) {
        if (totalCash < 0)
            throw new IllegalArgumentException(
                    "Cash cannot be negative");
        this.totalCash = totalCash;
    }

    public void dispenseCash(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        if (!hasSufficientCash(amount))
            throw new IllegalStateException(
                    "ATM has insufficient cash. " +
                            "Available: ₹" + totalCash);

        this.totalCash -= amount;
        System.out.println(
                "Dispensing ₹" + amount +
                        ". Remaining cash: ₹" + totalCash);
    }

    public void loadCash(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.totalCash += amount;
        System.out.println(
                "Cash loaded. Total: ₹" + totalCash);
    }

    public boolean hasSufficientCash(
            double amount) {
        return totalCash >= amount;
    }

    public double getTotalCash() {
        return totalCash;
    }
}