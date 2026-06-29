package com.practice.ATM_System.ATM_States;

import com.practice.ATM_System.ATM;
import com.practice.ATM_System.withdrawal.TwoThousandWithdraw;
import com.practice.ATM_System.withdrawal.FiveHundredWithdraw;
import com.practice.ATM_System.withdrawal.TwoHundredWithdraw;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.User;

public class WithdrawalState extends ATMState {
    @Override
    public void insertCard(ATM atm, ATMCard card) {
        System.out.println("Card already inserted");
    }

    @Override
    public int getBalance(ATM atm) {
        return atm.getBal();
    }

    @Override
    public String selectOptions(ATM atm) {
        System.out.println("Withdrawal in progress...");
        return "Withdrawal";
    }

    @Override
    public void deposit(ATM atm, int amount, int noteType) {
        System.out.println("Invalid in Withdrawal state");
    }

    @Override
    public void withdraw(ATM atm, int amt) {
        if (amt > atm.getBal()) {
            System.out.println("Insufficient funds in ATM");
            atm.setState(new IdleState());
            return;
        }
        // Chain of Responsibility
        TwoHundredWithdraw twoHundred = new TwoHundredWithdraw(null);
        FiveHundredWithdraw fiveHundred = new FiveHundredWithdraw(twoHundred);
        TwoThousandWithdraw twoThousand = new TwoThousandWithdraw(fiveHundred);

        twoThousand.withdrawal(atm, amt);
        atm.setBal(atm.getBal() - amt);
        System.out.println("Dispensed " + amt);
        atm.setState(new OperationSelectionState());
    }

    @Override
    public void validateCard(ATM atm, ATMCard card, User u) {
        System.out.println("Already validated");
    }
}
