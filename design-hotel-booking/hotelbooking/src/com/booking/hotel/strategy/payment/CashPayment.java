package com.booking.hotel.strategy.payment;

public class CashPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("The amount: " + amount + " was paid using Cash");
    }
}
