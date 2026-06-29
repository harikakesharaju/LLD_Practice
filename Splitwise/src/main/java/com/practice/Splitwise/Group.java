package com.practice.Splitwise;

import java.util.List;
import java.util.UUID;

public class Group {

	String id;
	String name;
	List<User> members;
	
	public Group(String s,List<User> mem) {
		this.id=UUID.randomUUID().toString();
		this.name=s;
		this.members=mem;
	}

	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public List<User> getMembers() {
		return members;
	}
	
	
}
