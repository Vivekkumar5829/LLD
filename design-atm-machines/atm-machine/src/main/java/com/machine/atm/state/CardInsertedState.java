package com.machine.atm.state;

import com.machine.atm.enums.ATMStateEnum;
import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public class CardInsertedState implements IATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException(
                "Card already inserted.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        Card card = atm.getCurrentCard();

        boolean valid = card.validatePin(pin);
        if (valid) {
            atm.setState(new PINEnteredState(),
                    ATMStateEnum.PIN_ENTERED);
            System.out.println(
                    "PIN correct. Select transaction.");
        } else {
            System.out.println("Wrong PIN. " +
                    (3 - card.getWrongAttempts()) +
                    " attempts remaining.");
        }
    }

    @Override
    public void selectTransaction(ATM atm,
                                  TransactionType type,
                                  double amount) {
        throw new IllegalStateException(
                "Enter PIN first.");
    }

    @Override
    public void dispenseCash(ATM atm,
                             double amount) {
        throw new IllegalStateException(
                "Enter PIN first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Card ejected.");
        atm.clearSession();
        atm.setState(new IdleState(),
                ATMStateEnum.IDLE);
    }
}