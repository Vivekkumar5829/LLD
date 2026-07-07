package com.machine.atm.validator;

import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public abstract class Validator {
    protected Validator next;

    public Validator setNext(Validator next) {
        this.next = next;
        return next; // enables chaining
    }

    public abstract boolean validate(
            ATM atm,
            Card card,
            String pin);

    protected boolean validateNext(
            ATM atm,
            Card card,
            String pin) {
        if (next == null) return true;
        return next.validate(atm, card, pin);
    }
}