package com.practice.RIdeSharingSystem.payment;

import java.util.UUID;

import com.practice.RIdeSharingSystem.enums.PaymentMethod;
import com.practice.RIdeSharingSystem.enums.PaymentStatus;

public class Payment {

	private String paymentId;
	private double amount;
	private PaymentMethod paymentMethod;
	private PaymentStatus status;
	
	public Payment(double amount, PaymentMethod paymentMethod) {
		this.paymentId = UUID.randomUUID().toString();
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = PaymentStatus.PENDING;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
}