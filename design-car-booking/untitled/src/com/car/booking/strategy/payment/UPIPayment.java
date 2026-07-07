package com.car.booking.strategy.payment;

import com.car.booking.model.enums.PaymentType;

public class UPIPayment implements PaymentStrategy{

    @Override
    public void processPayment(double amount) {
        System.out.println("Initiating UPI transfer of ₹" + amount +
                ". Sending to payment gateway.");
    }
}
