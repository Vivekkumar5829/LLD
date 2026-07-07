package com.machine.atm.observer;

import com.machine.atm.models.Transaction;


public class ReceiptPrinter
        implements ATMObserver {

    @Override
    public void onTransaction(
            Transaction transaction) {
        System.out.println(
                "\n[RECEIPT PRINTER]");
        transaction.printReceipt();
    }
}