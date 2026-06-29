package com.practice.Splitwise;

import java.util.List;

public interface SplitStrategy {
	
	GroupController gc=new GroupController();

	List<Split> calc(double amt,User paidby,List<User> mem,List<Double> splitval);
}
