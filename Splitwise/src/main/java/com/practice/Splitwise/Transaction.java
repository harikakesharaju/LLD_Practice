package com.practice.Splitwise;

public class Transaction {

	User from;
	User to;
	double amt;
	
	public Transaction(User f,User t,double a) {
		this.from=f;
		this.to=t;
		this.amt=a;
	}

	public User getFrom() {
		return from;
	}


	public User getTo() {
		return to;
	}

	public double getAmt() {
		return amt;
	}

	
}
