package com.practice.ATM_System.user;

public class User {

	BankAccount acc;
	ATMCard card;
	
	public User(BankAccount acc,ATMCard c) {
		this.acc=acc;
		this.card=c;
	}

	public BankAccount getAcc() {
		return acc;
	}

	public ATMCard getCard() {
		return card;
	}


	
}
