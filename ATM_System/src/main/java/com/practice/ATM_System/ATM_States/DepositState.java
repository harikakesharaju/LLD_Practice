package com.practice.ATM_System.ATM_States;

import com.practice.ATM_System.ATM;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.User;

public class DepositState extends ATMState {
    @Override
    public void insertCard(ATM atm, ATMCard card) {
        System.out.println("Card already inserted");
    }

    @Override
    public int getBalance(ATM atm) {
        System.out.println("Invalid in Deposit state");
        return -1;
    }

    @Override
    public String selectOptions(ATM atm) {
        System.out.println("Deposit in progress...");
        return "Deposit";
    }

    @Override
    public void deposit(ATM atm, int amount, int noteType) {
        if (noteType == 200) {
            atm.setNoof200(atm.getNoof200() + amount / 200);
        } else if (noteType == 500) {
            atm.setNoof500(atm.getNoof500() + amount / 500);
        } else if (noteType == 2000) {
            atm.setNoof2k(atm.getNoof2k() + amount / 2000);
        }
        atm.setBal(atm.getBal() + amount);
        System.out.println("Deposited " + amount);
        atm.setState(new OperationSelectionState());
    }

    @Override
    public void withdraw(ATM atm, int amount) {
        System.out.println("Invalid in Deposit state");
    }

    @Override
    public void validateCard(ATM atm, ATMCard card, User u) {
        System.out.println("Already validated");
    }
}
