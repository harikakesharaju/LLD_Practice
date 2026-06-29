package com.practice.Splitwise;

public class User {

	int id;
	String name;
	BalanceSheet balSheet;
	
	
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.balSheet = new BalanceSheet(this);
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public BalanceSheet getBalSheet() {
		return balSheet;
	}


//	public String getName() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}
