package com.car.booking.strategy.payment;

import com.car.booking.model.enums.PaymentType;

public class CashPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("Collecting cash of ₹" + amount +
                " from rider. No digital record.");
    }
}
