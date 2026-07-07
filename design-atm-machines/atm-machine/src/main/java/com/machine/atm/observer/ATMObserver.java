package com.machine.atm.observer;

import com.machine.atm.models.Transaction;

public interface ATMObserver {
    void onTransaction(Transaction transaction);
}
