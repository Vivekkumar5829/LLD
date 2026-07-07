package com.machine.atm.strategy;

import com.machine.atm.models.ATM;

public interface TransactionStrategy {
    void execute(ATM atm, double amount);
}