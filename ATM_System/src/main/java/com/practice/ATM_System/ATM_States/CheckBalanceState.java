package com.practice.ATM_System.ATM_States;

import com.practice.ATM_System.ATM;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.User;

public class CheckBalanceState extends ATMState {
    @Override
    public void insertCard(ATM atm, ATMCard card) {
        System.out.println("Card already inserted");
    }

    @Override
    public int getBalance(ATM atm) {
        System.out.println("Balance: " + atm.getBal());
        atm.setState(new OperationSelectionState());
        return atm.getBal();
    }

    @Override
    public String selectOptions(ATM atm) {
        System.out.println("Check balance operation");
        return "CheckBalance";
    }

    @Override
    public void deposit(ATM atm, int amount, int noteType) {
        System.out.println("Invalid in CheckBalance state");
    }

    @Override
    public void withdraw(ATM atm, int amount) {
        System.out.println("Invalid in CheckBalance state");
    }

    @Override
    public void validateCard(ATM atm, ATMCard card, User u) {
        System.out.println("Already validated");
    }
}
