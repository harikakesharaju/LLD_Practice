package com.practice.RIdeSharingSystem.payment;

import com.practice.RIdeSharingSystem.enums.PaymentMethod;
import com.practice.RIdeSharingSystem.enums.PaymentStatus;

public class CashPaymentStrategy implements PaymentStrategy {

	@Override
	public Payment pay(double amount) {

		System.out.println("Cash payment selected.");
		Payment payment = new Payment(amount, PaymentMethod.CASH);

		payment.setStatus(PaymentStatus.SUCCESS);
		return payment;
	}
}