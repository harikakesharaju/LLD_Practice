package com.practice.Splitwise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseController {
	
	 private GroupController groupController;

	    public ExpenseController(GroupController groupController) {
	        this.groupController = groupController;
	    }

	
	public List<Transaction> settleGroupExpenses(String gid){
		
		GroupController grp=new GroupController();
		Group g=grp.getGroup(gid);
		Map<User,Double> netbal=new HashMap<>();
		for(User mem:g.getMembers()) {
			double userbal=0;
			for(Map.Entry<User, Double> en:mem.getBalSheet().getBalances().entrySet()) {
				if(g.getMembers().contains(en.getKey())) {
					userbal+=en.getValue();
				}
			}
			netbal.put(mem, userbal);
		}
		
		List<Map.Entry<User, Double>> cred=netbal.entrySet().stream().filter(e->e.getValue()>0).collect(Collectors.toList());
		
		List<Map.Entry<User, Double>> debt=netbal.entrySet().stream().filter(e->e.getValue()<0).collect(Collectors.toList());
		
		cred.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		debt.sort(Map.Entry.comparingByValue());
		
		int i=0,j=0;
		List<Transaction> trans=new ArrayList<>();
		while(i<cred.size() && j<debt.size()) {
			Map.Entry<User,Double> c=cred.get(i);
			Map.Entry<User, Double> d=debt.get(j);
			
			double a=Math.min(c.getValue(), -d.getValue());
			trans.add(new Transaction(d.getKey(),c.getKey(),a));
			
			c.setValue(c.getValue()-a);
			d.setValue(d.getValue()+a);
			
			if(Math.abs(c.getValue())<0.01) i++;
			if(Math.abs(d.getValue())<0.01) j++;
 		}
		return trans;
	}

//	public List<Transaction> settleGroupExpenses(String gid) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
