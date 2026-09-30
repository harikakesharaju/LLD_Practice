package com.practice.RIdeSharingSystem.payment;

public interface PaymentStrategy {

    Payment pay(double amount);
}