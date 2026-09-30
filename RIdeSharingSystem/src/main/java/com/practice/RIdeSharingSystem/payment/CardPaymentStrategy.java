package com.practice.RIdeSharingSystem.payment;

import com.practice.RIdeSharingSystem.enums.PaymentMethod;
import com.practice.RIdeSharingSystem.enums.PaymentStatus;

public class CardPaymentStrategy implements PaymentStrategy {

	@Override
	public Payment pay(double amount) {

		System.out.println("Processing card payment...");
		Payment payment = new Payment(amount, PaymentMethod.CARD);
		
		// Simulate successful payment
		payment.setStatus(PaymentStatus.SUCCESS);
		return payment;
	}
}