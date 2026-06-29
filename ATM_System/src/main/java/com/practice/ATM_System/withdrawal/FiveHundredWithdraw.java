package com.practice.ATM_System.withdrawal;

import com.practice.ATM_System.ATM;

public class FiveHundredWithdraw implements Withdrawal{

Withdrawal nextWithdraw;
	
	public FiveHundredWithdraw(Withdrawal nxt) {
		this.nextWithdraw=nxt;
	}
	
	// FiveHundredWithdraw.java
	public void withdrawal(ATM atm, int amt) {
	    int t = Math.min(amt / 500, atm.getNoof500());
	    int used = t * 500;
	    atm.setNoof500(atm.getNoof500() - t);
	    int rem = amt - used;
	    if (rem > 0 && nextWithdraw != null) {
	        nextWithdraw.withdrawal(atm, rem);
	    }
	}
}
