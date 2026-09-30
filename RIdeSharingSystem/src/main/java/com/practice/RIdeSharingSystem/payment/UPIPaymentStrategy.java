package com.practice.RIdeSharingSystem.payment;

import com.practice.RIdeSharingSystem.enums.PaymentMethod;
import com.practice.RIdeSharingSystem.enums.PaymentStatus;

public class UPIPaymentStrategy implements PaymentStrategy {

	@Override
	public Payment pay(double amount) {

		System.out.println("Processing UPI payment...");
		Payment payment = new Payment(amount, PaymentMethod.UPI);

		payment.setStatus(PaymentStatus.SUCCESS);
		return payment;
	}
}