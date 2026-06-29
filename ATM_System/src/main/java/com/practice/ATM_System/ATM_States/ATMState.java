package com.practice.ATM_System.ATM_States;

import com.practice.ATM_System.ATM;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.User;

public abstract class ATMState {
    public abstract void insertCard(ATM atm, ATMCard card);
    public void exit(ATM atm) {
        System.out.println("Exit State");
        atm.setState(new IdleState());
    }
    public abstract int getBalance(ATM atm);
    public abstract String selectOptions(ATM atm);
    public abstract void deposit(ATM atm, int amount, int noteType);
    public abstract void withdraw(ATM atm, int amount);
    public abstract void validateCard(ATM atm, ATMCard card, User u);
}
