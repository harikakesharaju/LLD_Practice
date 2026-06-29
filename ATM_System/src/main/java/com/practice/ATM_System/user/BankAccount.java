package com.practice.ATM_System.user;

public class BankAccount {
	int accNo;
	int balance;
	
	public BankAccount(int acc,int bal) {
		this.accNo=acc;
		this.balance=bal;
	}
	
	int getBal() {
		return this.balance;
	}
	
	int getAccNo() {
		return accNo;
	}
}
