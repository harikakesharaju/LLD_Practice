package com.practice.Splitwise;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy{

	@Override
	public List<Split> calc(double amt, User paidby, List<User> mem, List<Double> splitval) {
		// TODO Auto-generated method stub
		List<Split> sp=new ArrayList<>();
		double a=amt/mem.size();
		for(User par:mem) {
			sp.add(new Split(par,a));
		}
		return sp;
	}

}
