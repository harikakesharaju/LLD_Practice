package com.practice.ATM_System.ATM_States;

import com.practice.ATM_System.ATM;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.User;

import java.util.Scanner;

public class OperationSelectionState extends ATMState {
    @Override
    public void insertCard(ATM atm, ATMCard card) {
        System.out.println("Invalid in OperationSelection state");
    }

    @Override
    public int getBalance(ATM atm) {
        System.out.println("Invalid in OperationSelection state");
        return -1;
    }

    @Override
    public String selectOptions(ATM atm) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select option:\n1. Withdraw\n2. Deposit\n3. Check Balance");
        int op = sc.nextInt();
        if (op == 1) {
            atm.setState(new WithdrawalState());
            return "Withdraw";
        } else if (op == 2) {
            atm.setState(new DepositState());
            return "Deposit";
        } else if (op == 3) {
            atm.setState(new CheckBalanceState());
            return "CheckBalance";
        } else {
            System.out.println("Invalid option");
            return "Invalid";
        }
    }

    @Override
    public void deposit(ATM atm, int amount, int noteType) {
        System.out.println("Invalid in OperationSelection state");
    }

    @Override
    public void withdraw(ATM atm, int amount) {
        System.out.println("Invalid in OperationSelection state");
    }

    @Override
    public void validateCard(ATM atm, ATMCard card, User u) {
        System.out.println("Already validated");
    }
}
