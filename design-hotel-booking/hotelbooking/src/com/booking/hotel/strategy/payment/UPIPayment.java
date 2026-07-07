package com.booking.hotel.strategy.payment;

public class UPIPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("The amount: " + amount + " was paid using UPI");
    }
}
