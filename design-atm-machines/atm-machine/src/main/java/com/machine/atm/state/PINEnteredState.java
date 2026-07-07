package com.machine.atm.state;

import com.machine.atm.enums.ATMStateEnum;
import com.machine.atm.enums.TransactionType;
import com.machine.atm.models.ATM;
import com.machine.atm.models.Account;
import com.machine.atm.models.Card;
import com.machine.atm.models.Transaction;

public class PINEnteredState implements IATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException(
                "Session already active.");
    }

    @Override
    public void enterPin(ATM atm, String pin) {
        throw new IllegalStateException(
                "PIN already entered.");
    }

    @Override
    public void selectTransaction(ATM atm,
                                  TransactionType type,
                                  double amount) {
        Account account = atm.getCurrentAccount();

        switch (type) {
            case WITHDRAWAL:
                if (!account.hasSufficientBalance(
                        amount)) {
                    System.out.println(
                            "Insufficient balance.");
                    return;
                }
                if (!atm.getCashDispenser()
                        .hasSufficientCash(amount)) {
                    System.out.println(
                            "ATM has insufficient cash.");
                    return;
                }
                atm.setState(
                        new TransactionState(type, amount),
                        ATMStateEnum.TRANSACTION);
                atm.dispenseCash(amount);
                break;

            case DEPOSIT:
                account.credit(amount);
                Transaction depositTx =
                        new Transaction(
                                account, type,
                                amount, true);
                depositTx.printReceipt();
                atm.setState(new IdleState(),
                        ATMStateEnum.IDLE);
                atm.clearSession();
                break;

            case BALANCE_INQUIRY:
                System.out.println(
                        "Available balance: ₹" +
                                account.getBalance());
                Transaction balTx =
                        new Transaction(
                                account, type, 0, true);
                balTx.printReceipt();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown transaction type.");
        }
    }

    @Override
    public void dispenseCash(ATM atm,
                             double amount) {
        throw new IllegalStateException(
                "Select transaction first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Card ejected.");
        atm.clearSession();
        atm.setState(new IdleState(),
                ATMStateEnum.IDLE);
    }
}