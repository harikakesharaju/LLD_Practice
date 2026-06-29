package com.practice.CarRentalSystem;

public class User {

	int userid;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	String name;
	public User(int userid, String name) {
		super();
		this.userid = userid;
		this.name = name;
	}
}
