package com.practice.Splitwise;

import java.util.ArrayList;
import java.util.List;

public class PercentSplitStrategy implements SplitStrategy {

	
	@Override
	public List<Split> calc(double amt, User paidby, List<User> mem, List<Double> splitval) {
		// TODO Auto-generated method stub
		List<Split> sp=new ArrayList<>();
		int i=0;
		for(User par:mem) {
			double a= (splitval.get(i)/100)*amt;
			sp.add(new Split(mem.get(i),amt));
			i++;
		}
		return sp;
	}
}
