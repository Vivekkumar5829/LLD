package com.machine.atm.validator;

import com.machine.atm.models.ATM;
import com.machine.atm.models.Card;

public class BalanceValidator extends Validator {
    private final double requiredAmount;

    public BalanceValidator(double requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    @Override
    public boolean validate(ATM atm,
                            Card card,
                            String pin) {
        boolean sufficient = atm
                .getCurrentAccount()
                .hasSufficientBalance(requiredAmount);

        if (!sufficient) {
            System.out.println(
                    "Validation failed: " +
                            "Insufficient balance. " +
                            "Available: ₹" +
                            atm.getCurrentAccount()
                                    .getBalance());
            return false;
        }

        System.out.println(
                "Balance validation passed.");
        return validateNext(atm, card, pin);
    }
}