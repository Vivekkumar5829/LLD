package com.machine.atm.state;

import com.machine.atm.enums.ATMStateEnum;
import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;
import com.machine.atm.models.Transaction;

public class TransactionState implements IATMState {

    private final TransactionType type;
    private final double amount;

    public TransactionState(TransactionType type,
                            double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException(
                "Transaction in progress.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        throw new IllegalStateException(
                "Transaction in progress.");
    }

    @Override
    public void selectTransaction(ATM atm,
                                  TransactionType t,
                                  double amount) {
        throw new IllegalStateException(
                "Transaction in progress.");
    }

    @Override
    public void dispenseCash(ATM atm,
                             double amount) {
        // Debit account
        atm.getCurrentAccount().debit(amount);

        // Dispense cash
        atm.getCashDispenser().dispenseCash(amount);

        // Create transaction record
        Transaction tx = new Transaction(
                atm.getCurrentAccount(),
                type, amount, true);

        // Print receipt
        tx.printReceipt();

        // Move to dispensing state
        atm.setState(new DispensingState(),
                ATMStateEnum.DISPENSING);
    }

    @Override
    public void ejectCard(ATM atm) {
        throw new IllegalStateException(
                "Cannot eject during transaction.");
    }
}