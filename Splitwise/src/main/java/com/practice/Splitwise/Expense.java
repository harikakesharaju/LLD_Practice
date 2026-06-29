package com.practice.Splitwise;

import java.util.List;

public class Expense {

	int id;
	String desc;
	int amt;
	List<Split> splits;
	User paidBy;

	private Expense(ExpenseBuilder builder) {
		this.id=builder.id;
		this.desc=builder.desc;
		this.paidBy=builder.paidBy;
		this.amt=builder.amt;
		this.splits=builder.stra.calc(amt, paidBy, builder.part, builder.splitval);
	}
	

	public static class ExpenseBuilder {
		int id;
		String desc;
		int amt;
		List<Split> splits;
		User paidBy;
		List<User> part;
		List<Double> splitval;
		SplitStrategy stra;
		public ExpenseBuilder setId(int id) {
			this.id=id;
			return this;
		}
		public ExpenseBuilder setDesc(String d) {
			this.desc=d;
			return this;
		}
		public ExpenseBuilder setAmt(int id) {
			this.amt=id;
			return this;
		}
		public ExpenseBuilder setPaidBy(User id) {
			this.paidBy=id;
			return this;
		}
		public ExpenseBuilder setSplitStra(SplitStrategy stra) {
			this.stra=stra;
			return this;
		} 
		public ExpenseBuilder setSplitVal(List<Double> val) {
			this.splitval=val;
			return this;
		} 
		public ExpenseBuilder setParts(List<User> p) {
			this.part=p;
			return this;
		}
		public Expense build() {
			return new Expense(this);
		}
	}
}
