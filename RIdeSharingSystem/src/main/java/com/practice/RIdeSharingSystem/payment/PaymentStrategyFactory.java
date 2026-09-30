package com.practice.RIdeSharingSystem.payment;

import com.practice.RIdeSharingSystem.enums.PaymentMethod;

public class PaymentStrategyFactory {

    public static PaymentStrategy getStrategy(
            PaymentMethod method) {

        switch (method) {

            case CARD:
                return new CardPaymentStrategy();

            case UPI:
                return new UPIPaymentStrategy();

            case CASH:
                return new CashPaymentStrategy();

            default:
                throw new IllegalArgumentException(
                        "Unsupported payment method"
                );
        }
    }
}