package com.car.booking.strategy.payment;

import com.car.booking.model.enums.PaymentType;

public class CardPayment implements PaymentStrategy{

    @Override
    public void processPayment(double amount) {
        System.out.println("Charging ₹" + amount +
                " to card. Sending bank request.");
    }
}
