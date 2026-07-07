package com.machine.atm.validator;

import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public class PINValidator extends Validator {

    @Override
    public boolean validate(ATM atm,
                            Card card,
                            String pin) {
        boolean valid = card.validatePin(pin);

        if (!valid) {
            System.out.println(
                    "Validation failed: Wrong PIN. " +
                            (3 - card.getWrongAttempts()) +
                            " attempts remaining.");
            return false;
        }

        System.out.println(
                "PIN validation passed.");
        return validateNext(atm, card, pin);
    }
}