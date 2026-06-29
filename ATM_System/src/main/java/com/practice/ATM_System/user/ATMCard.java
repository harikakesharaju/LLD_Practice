package com.practice.ATM_System.user;

public class ATMCard {
	int cardNumber;
	int cvv;
	String fullName;
	int accName;
	
	public ATMCard(int cardNumber, int cvv, String fullName, int accName) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.fullName = fullName;
		this.accName = accName;
	}
	
	
	public boolean validateCard(User u) {
		return u.getAcc().getAccNo()==this.accName;
	}
}
