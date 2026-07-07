package com.machine.atm;

import com.machine.atm.enums.*;
import com.machine.atm.factory.CardFactory;
import com.machine.atm.models.*;
import com.machine.atm.strategy.*;
import com.machine.atm.validator.*;

public class Main {
    public static void main(String[] args) {

        // Setup
        User user = new User(
                "Vivek", "9999999999",
                "Pune", 23);

        Account account = new Account(
                "ACC001", user,
                50000.0,
                AccountType.SAVING_ACCOUNT);

        // Use Factory for card creation
        Card card = CardFactory.createCard(
                CardType.DEBIT_CARD,
                "4111111111111111",
                "1234", account);

        CashDispenser dispenser =
                new CashDispenser(100000.0);

        ATM atm = ATM.getInstance(
                "ATM001", dispenser);

        // ── Scenario 1: Full validation chain
        //    + Withdrawal ──────────────────────
        System.out.println(
                "=== WITHDRAWAL WITH VALIDATION ===");

        // Build chain
        Validator chain = new CardValidator();
        chain.setNext(new PINValidator())
                .setNext(new BalanceValidator(5000));

        atm.insertCard(card);

        boolean valid = chain.validate(
                atm, card, "1234");
        if (valid) {
            new WithdrawalStrategy()
                    .execute(atm, 5000);
        }
        atm.ejectCard();

        // ── Scenario 2: Deposit ─────────────
        System.out.println(
                "\n=== DEPOSIT TEST ===");
        atm.insertCard(card);

        Validator depositChain =
                new CardValidator();
        depositChain.setNext(
                new PINValidator());

        valid = depositChain.validate(
                atm, card, "1234");
        if (valid) {
            new DepositStrategy()
                    .execute(atm, 10000);
        }
        atm.ejectCard();

        // ── Scenario 3: Balance Inquiry ─────
        System.out.println(
                "\n=== BALANCE INQUIRY ===");
        atm.insertCard(card);

        valid = new CardValidator()
                .validate(atm, card, "1234");
        if (valid) {
            new BalanceInquiryStrategy()
                    .execute(atm, 0);
        }
        atm.ejectCard();

        // ── Scenario 4: Wrong PIN ───────────
        System.out.println(
                "\n=== WRONG PIN TEST ===");
        Card card2 = CardFactory.createCard(
                CardType.DEBIT_CARD,
                "4222222222222222",
                "5678", account);

        atm.insertCard(card2);
        Validator pinChain = new CardValidator();
        pinChain.setNext(new PINValidator());

        try {
            pinChain.validate(atm, card2, "0000");
            pinChain.validate(atm, card2, "0000");
            pinChain.validate(atm, card2, "0000");
        } catch (IllegalStateException e) {
            System.out.println(
                    "Expected: " + e.getMessage());
        }
        atm.ejectCard();

        // ── Scenario 5: Insufficient Balance
        System.out.println(
                "\n=== INSUFFICIENT BALANCE ===");
        atm.insertCard(card);
        Validator balChain = new CardValidator();
        balChain.setNext(new PINValidator())
                .setNext(
                        new BalanceValidator(200000));

        valid = balChain.validate(
                atm, card, "1234");
        if (valid) {
            new WithdrawalStrategy()
                    .execute(atm, 200000);
        }
        atm.ejectCard();
    }
}