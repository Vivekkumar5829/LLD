package com.machine.atm.models;

import com.machine.atm.enums.ATMStateEnum;
import com.machine.atm.enums.TransactionType;
import com.machine.atm.state.IdleState;
import com.machine.atm.state.IATMState;
import com.machine.atm.observer.ATMObserver;
import java.util.ArrayList;
import java.util.List;

public class ATM {

    // Singleton
    private static volatile ATM instance;
    private final List<ATMObserver> observers = new ArrayList<>();

    // Fields
    private final String atmId;
    private final CashDispenser cashDispenser;

    // State Pattern
    private IATMState currentState;
    private ATMStateEnum stateLabel;

    // Current session data
    private Card currentCard;
    private Account currentAccount;

    // Private constructor
    private ATM(String atmId,
                CashDispenser cashDispenser) {
        if (atmId == null ||
                atmId.trim().isEmpty())
            throw new IllegalArgumentException(
                    "ATM ID cannot be empty");
        if (cashDispenser == null)
            throw new IllegalArgumentException(
                    "CashDispenser cannot be null");

        this.atmId = atmId;
        this.cashDispenser = cashDispenser;
        this.currentState = new IdleState();
        this.stateLabel = ATMStateEnum.IDLE;
    }

    // Double checked locking Singleton
    public static ATM getInstance(
            String atmId,
            CashDispenser cashDispenser) {
        if (instance == null) {
            synchronized (ATM.class) {
                if (instance == null) {
                    instance = new ATM(
                            atmId, cashDispenser);
                }
            }
        }
        return instance;
    }

    // Delegate to current state
    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void selectTransaction(
            TransactionType type,
            double amount) {
        currentState.selectTransaction(
                this, type, amount);
    }

    public void dispenseCash(double amount) {
        currentState.dispenseCash(this, amount);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }

    // Called by State objects only
    // package-private
    public void setState(IATMState state,
                         ATMStateEnum label) {
        this.currentState = state;
        this.stateLabel = label;
    }

    // Session management
    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public void clearSession() {
        this.currentCard = null;
        this.currentAccount = null;
    }

    public void setCurrentAccount(
            Account account) {
        this.currentAccount = account;
    }

    public void addObserver(ATMObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Transaction transaction) {
        for (ATMObserver observer : observers) {
            observer.onTransaction(transaction);
        }
    }

    // Getters
    public String getAtmId() { return atmId; }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public ATMStateEnum getStateLabel() {
        return stateLabel;
    }
}