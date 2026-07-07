package com.car.booking.strategy.payment;

import com.car.booking.model.enums.PaymentType;

public interface PaymentStrategy {
     void processPayment(double amount);
}
