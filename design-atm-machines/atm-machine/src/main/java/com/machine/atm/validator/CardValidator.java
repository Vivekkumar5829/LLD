package com.machine.atm.validator;

import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public class CardValidator extends Validator {

    @Override
    public boolean validate(ATM atm,
                            Card card,
                            String pin) {
        // Check if card is blocked
        if (card.isBlocked()) {
            System.out.println(
                    "Validation failed: " +
                            "Card is blocked.");
            return false;
        }

        // Check if card is expired
        if (card.isExpired()) {
            System.out.println(
                    "Validation failed: " +
                            "Card is expired.");
            return false;
        }

        System.out.println(
                "Card validation passed.");
        return validateNext(atm, card, pin);
    }
}