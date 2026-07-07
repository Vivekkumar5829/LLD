package com.machine.atm.factory;

import com.machine.atm.enums.CardType;
import com.machine.atm.models.Account;
import com.machine.atm.models.Card;

import java.time.LocalDate;

public class CardFactory {

    public static Card createCard(
            CardType type,
            String cardNumber,
            String pin,
            Account account) {
        switch (type) {
            case DEBIT_CARD:
                return new Card(
                        cardNumber, pin,
                        CardType.DEBIT_CARD,
                        LocalDate.now().plusYears(5),
                        account);

            case CREDIT_CARD:
                return new Card(
                        cardNumber, pin,
                        CardType.CREDIT_CARD,
                        LocalDate.now().plusYears(3),
                        account);

            default:
                throw new
                        IllegalArgumentException(
                        "Unknown card type: " +
                                type);
        }
    }
}