package com.practice.Splitwise;

import java.util.ArrayList;
import java.util.List;

public class GroupController {

	List<Group> grps;
	List<User> users;
	
	public GroupController(){
		grps=new ArrayList<>();
		users=new ArrayList<>();
	}
	
	public Group addGroup(String gid,List<User> part) {
		Group g=new Group(gid,part);
		grps.add(g);
		return g;
	}
	
	public User addUser(int id,String n) {
		User u=new User(id,n);
		users.add(u);
		return u;
	}
	
	public Group getGroup(String id) {
	    return grps.stream()
	               .filter(g -> g.getId().equals(id))
	               .findFirst()
	               .orElse(null);
	}
	
	public User getUser(int id) {
		return users.stream().filter(u->u.getId()==(id)).findFirst().orElse(null);
	}
}
