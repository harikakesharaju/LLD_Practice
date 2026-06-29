package com.practice.ATM_System.withdrawal;

import com.practice.ATM_System.ATM;

public class TwoThousandWithdraw implements Withdrawal{

	Withdrawal nextWithdraw;
	
	public TwoThousandWithdraw(Withdrawal nxt) {
		this.nextWithdraw=nxt;
	}
	
	// TwoThousandWithdraw.java
	public void withdrawal(ATM atm, int amt) {
	    int t = Math.min(amt / 2000, atm.getNoof2k());
	    int used = t * 2000;
	    atm.setNoof2k(atm.getNoof2k() - t);
	    int rem = amt - used;
	    if (rem > 0 && nextWithdraw != null) {
	        nextWithdraw.withdrawal(atm, rem);
	    }
	}
}
