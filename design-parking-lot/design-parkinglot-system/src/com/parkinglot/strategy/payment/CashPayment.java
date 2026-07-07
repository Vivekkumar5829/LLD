package com.parkinglot.strategy.payment;

public class CashPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("The amount of " + amount + " was paid using cash. Money collected.") ;
    }
}
