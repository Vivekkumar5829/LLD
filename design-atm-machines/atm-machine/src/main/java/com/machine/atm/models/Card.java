package com.machine.atm.models;

import com.machine.atm.enums.CardType;
import java.time.LocalDate;
import java.util.UUID;

public class Card {
    private final String cardId;
    private final String cardNumber;
    private final String pin;
    private final CardType cardType;
    private final LocalDate expiryDate;
    private final Account linkedAccount;
    private boolean isBlocked;
    private int wrongPinAttempts;

    private static final int MAX_ATTEMPTS = 3;

    public Card(String cardNumber,
                String pin,
                CardType cardType,
                LocalDate expiryDate,
                Account linkedAccount) {
        if (cardNumber == null ||
                cardNumber.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Card number cannot be empty");
        if (pin == null || pin.length() != 4)
            throw new IllegalArgumentException(
                    "PIN must be 4 digits");

        this.cardId = UUID.randomUUID().toString();
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.linkedAccount = linkedAccount;
        this.isBlocked = false;
        this.wrongPinAttempts = 0;
    }

    // Validate PIN
    public boolean validatePin(String enteredPin) {
        if (isBlocked)
            throw new IllegalStateException(
                    "Card is blocked.");

        if (this.pin.equals(enteredPin)) {
            wrongPinAttempts = 0; // reset on success
            return true;
        }

        wrongPinAttempts++;
        if (wrongPinAttempts >= MAX_ATTEMPTS) {
            isBlocked = true;
            throw new IllegalStateException(
                    "Card blocked after " +
                            MAX_ATTEMPTS + " wrong attempts.");
        }
        return false;
    }

    // Check if card is expired
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean isBlocked() { return isBlocked; }
    public String getCardNumber() { return cardNumber; }
    public CardType getCardType() { return cardType; }
    public Account getLinkedAccount() {
        return linkedAccount;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }




    // Add this getter to Card.java
    public int getWrongAttempts() {
        return wrongPinAttempts;
    }
}