package com.machine.atm.state;

import com.machine.atm.enums.ATMStateEnum;
import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public class DispensingState implements IATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException(
                "Please collect cash first.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        throw new IllegalStateException(
                "Please collect cash first.");
    }

    @Override
    public void selectTransaction(ATM atm,
                                  TransactionType type,
                                  double amount) {
        throw new IllegalStateException(
                "Please collect cash first.");
    }

    @Override
    public void dispenseCash(ATM atm,
                             double amount) {
        throw new IllegalStateException(
                "Already dispensing cash.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println(
                "Please collect your cash and card.");
        atm.clearSession();
        atm.setState(new IdleState(),
                ATMStateEnum.IDLE);
        System.out.println(
                "Thank you. Goodbye!");
    }
}