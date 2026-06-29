package com.practice.ATM_System.ATM_States;

import com.practice.ATM_System.ATM;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.User;

public class HasCardState extends ATMState {
    @Override
    public void insertCard(ATM atm, ATMCard card) {
        System.out.println("Card already inserted");
    }

    @Override
    public int getBalance(ATM atm) {
        System.out.println("Invalid in HasCard state");
        return -1;
    }

    @Override
    public String selectOptions(ATM atm) {
        System.out.println("Invalid in HasCard state");
        return "Invalid";
    }

    @Override
    public void deposit(ATM atm, int amount, int noteType) {
        System.out.println("Invalid in HasCard state");
    }

    @Override
    public void withdraw(ATM atm, int amount) {
        System.out.println("Invalid in HasCard state");
    }

    @Override
    public void validateCard(ATM atm, ATMCard card, User u) {
        boolean valid = card.validateCard(u);
        if (valid) {
            System.out.println("Card validated successfully");
            atm.setState(new OperationSelectionState());
        } else {
            System.out.println("Card and User details don’t match");
            atm.setState(new IdleState());
        }
    }
}
