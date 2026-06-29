package com.practice.ATM_System;

import java.util.Scanner;

import com.practice.ATM_System.ATM_States.CheckBalanceState;
import com.practice.ATM_System.ATM_States.IdleState;
import com.practice.ATM_System.user.ATMCard;
import com.practice.ATM_System.user.BankAccount;
import com.practice.ATM_System.user.User;

/**
 * Hello world!
 *
 */
public class ATMMain {
    public static void main(String[] args) {
        ATM atm = new ATM(10, 10, 5, new IdleState()); // 10x200, 10x500, 5x2000

        User u = new User(new BankAccount(12345, 5000),
                          new ATMCard(1111, 123, "John Doe", 12345));

        // Insert card
        atm.getState().insertCard(atm, u.getCard());

        // Validate card
        atm.getState().validateCard(atm, u.getCard(), u);

//        int amt=atm.getState().getBalance(atm);
//        System.out.println("Current balance is "+amt);
        // Select operation
        while(true) {
            String s=atm.getState().selectOptions(atm); // user chooses withdrawal

            // Withdraw
            if(s.toLowerCase().equals("withdraw"))
            atm.getState().withdraw(atm, 2700);
            else if(s.toLowerCase().equals("deposit"))
            	atm.getState().deposit(atm, 1000, 500);
            else {
            	int amt=atm.getState().getBalance(atm);
                System.out.println("Current balance is "+amt);
            }
            System.out.println("Wanna continue(Y/N)");
            Scanner sc=new Scanner(System.in);
            String ch=sc.next();
            if(ch=="N") break;
            
        }
   
        
    }
}
