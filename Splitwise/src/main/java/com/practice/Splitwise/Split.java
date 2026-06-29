package com.practice.Splitwise;

public class Split {

	User user;
	double amt;
	
	public Split(User u,double a) {
		this.amt=a;
		this.user=u;
	}
	
	public User getUser() {
		return user;
	}
	public double getAmt() {
		return amt;
	}
}
