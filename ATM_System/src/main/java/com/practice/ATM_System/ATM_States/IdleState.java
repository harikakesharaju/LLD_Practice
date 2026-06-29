package com.practice.ATM_System.ATM_States;

import com.practice.ATM_System.ATM;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.User;

public class IdleState extends ATMState {
    @Override
    public void insertCard(ATM atm, ATMCard card) {
        System.out.println("Card inserted.");
        atm.setState(new HasCardState());
    }

    @Override
    public int getBalance(ATM atm) {
        System.out.println("Invalid in Idle state");
        return -1;
    }

    @Override
    public String selectOptions(ATM atm) {
        System.out.println("Invalid in Idle state");
        return "Invalid";
    }

    @Override
    public void deposit(ATM atm, int amount, int noteType) {
        System.out.println("Invalid in Idle state");
    }

    @Override
    public void withdraw(ATM atm, int amount) {
        System.out.println("Invalid in Idle state");
    }

    @Override
    public void validateCard(ATM atm, ATMCard card, User u) {
        System.out.println("No card to validate");
    }
}
