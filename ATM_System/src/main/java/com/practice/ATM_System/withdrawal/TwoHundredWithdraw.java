package com.practice.ATM_System.withdrawal;

import com.practice.ATM_System.ATM;

public class TwoHundredWithdraw  implements Withdrawal{
	
Withdrawal nextWithdraw;
	
	public TwoHundredWithdraw(Withdrawal nxt) {
		this.nextWithdraw=nxt;
	}
	

	// TwoHundredWithdraw.java
	public void withdrawal(ATM atm, int amt) {
	    int t = Math.min(amt / 200, atm.getNoof200());
	    int used = t * 200;
	    atm.setNoof200(atm.getNoof200() - t);
	    int rem = amt - used;
	    if (rem > 0 && nextWithdraw != null) {
	        nextWithdraw.withdrawal(atm, rem);
	    }
	}

}
