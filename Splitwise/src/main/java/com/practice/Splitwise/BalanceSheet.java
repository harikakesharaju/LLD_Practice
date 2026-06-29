package com.practice.Splitwise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {

	User owner;
	
	Map<User,Double> bal=new ConcurrentHashMap<>();
	
	public BalanceSheet(User u) {
		this.owner=u;
	}
	
	public void adjustBal(User u,double amt) {
		if(u.equals(owner)) {
			System.out.println("cannt owe urself");
		}
		bal.merge(u, amt, Double::sum);
	}
	
	public Map<User,Double> getBalances(){
		return bal;
	}
	
	public void showBalances() {
		System.out.println("Balance sheet of " + owner.getName());

		if (bal.isEmpty()) {
			System.out.println("All settled up");
		}
		double totOwedToMe = 0, totowe = 0;
		for (Map.Entry<User, Double> en : bal.entrySet()) {
			User oth = en.getKey();
			double a = en.getValue();
			if (a > 0.01) {
				System.out.println(oth.getName() + "owes " + owner.getName() + "- " + String.format("%.2f", a));
				totOwedToMe += a;
			} else if (a < -0.01) {
				System.out.println(owner.getName() + "owes " + oth.getName() + "- " + String.format("%.2f", -a));
				totowe += (-a);
			}
		}
		System.out.println("Total owed to the user =" + totOwedToMe);
		System.out.println("Total user Owes to others = " + totowe);
	}
}
