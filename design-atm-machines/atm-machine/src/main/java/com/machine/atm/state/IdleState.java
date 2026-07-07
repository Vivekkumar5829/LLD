package com.machine.atm.state;

import com.machine.atm.enums.ATMStateEnum;
import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public class IdleState implements IATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        if (card.isBlocked())
            throw new IllegalStateException(
                    "Card is blocked.");
        if (card.isExpired())
            throw new IllegalStateException(
                    "Card is expired.");

        atm.setCurrentCard(card);
        atm.setCurrentAccount(
                card.getLinkedAccount());
        atm.setState(new CardInsertedState(),
                ATMStateEnum.CARD_INSERTED);
        System.out.println("Card inserted. " +
                "Please enter PIN.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        throw new IllegalStateException(
                "Insert card first.");
    }

    @Override
    public void selectTransaction(ATM atm,
                                  TransactionType type,
                                  double amount) {
        throw new IllegalStateException(
                "Insert card first.");
    }

    @Override
    public void dispenseCash(ATM atm,
                             double amount) {
        throw new IllegalStateException(
                "Insert card first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        throw new IllegalStateException(
                "No card inserted.");
    }
}