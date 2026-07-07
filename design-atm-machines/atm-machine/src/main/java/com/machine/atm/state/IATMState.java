package com.machine.atm.state;


import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public interface IATMState {

    void insertCard(ATM atm, Card card);

    void enterPin(ATM atm, String pin);

    void selectTransaction(ATM atm,
                           TransactionType type,
                           double amount);

    void dispenseCash(ATM atm, double amount);

    void ejectCard(ATM atm);
}