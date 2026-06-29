package com.practice.Splitwise;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        GroupController gc = new GroupController();

        // Users
        User harika = gc.addUser(1, "Harika");
        User ram = gc.addUser(2, "Ram");
        User shyam = gc.addUser(3, "Shyam");
        User priya = gc.addUser(4, "Priya");

        // Group
        Group trip = gc.addGroup(
                "GOA_TRIP",
                Arrays.asList(harika, ram, shyam, priya)
        );

        //---------------------------------------------------------
        // Expense 1
        // Harika paid 4000 for dinner
        //---------------------------------------------------------

        Expense dinner = new Expense.ExpenseBuilder()
                .setId(101)
                .setDesc("Dinner")
                .setAmt(4000)
                .setPaidBy(harika)
                .setParts(trip.getMembers())
                .setSplitStra(new EqualSplitStrategy())
                .setSplitVal(null)
                .build();

        updateBalances(dinner);

        //---------------------------------------------------------
        // Expense 2
        // Ram paid 2000 for cab
        //---------------------------------------------------------

        Expense cab = new Expense.ExpenseBuilder()
                .setId(102)
                .setDesc("Cab")
                .setAmt(2000)
                .setPaidBy(ram)
                .setParts(trip.getMembers())
                .setSplitStra(new EqualSplitStrategy())
                .setSplitVal(null)
                .build();

        updateBalances(cab);

        //---------------------------------------------------------
        // Expense 3
        // Shyam paid 3000 for hotel
        //---------------------------------------------------------

        Expense hotel = new Expense.ExpenseBuilder()
                .setId(103)
                .setDesc("Hotel")
                .setAmt(3000)
                .setPaidBy(shyam)
                .setParts(trip.getMembers())
                .setSplitStra(new EqualSplitStrategy())
                .setSplitVal(null)
                .build();

        updateBalances(hotel);

        //---------------------------------------------------------
        // Print balance sheets
        //---------------------------------------------------------

        System.out.println("\n===== BALANCE SHEETS =====\n");

        harika.getBalSheet().showBalances();
        System.out.println();

        ram.getBalSheet().showBalances();
        System.out.println();

        shyam.getBalSheet().showBalances();
        System.out.println();

        priya.getBalSheet().showBalances();

        //---------------------------------------------------------
        // Settle group
        //---------------------------------------------------------

        ExpenseController ec = new ExpenseController(gc);

        List<Transaction> transactions =
                ((ExpenseController) ec).settleGroupExpenses("GOA_TRIP");

        System.out.println("\n===== SETTLEMENTS =====\n");

        for(Transaction t : transactions) {
            System.out.println(
                    t.getFrom().getName()
                    + " pays "
                    + t.getTo().getName()
                    + " : "
                    + String.format("%.2f", t.getAmt())
            );
        }
    }
    
    private static void updateBalances(Expense exp) {

        User paidBy = exp.paidBy;

        for(Split split : exp.splits) {

            User participant = split.getUser();

            if(participant.equals(paidBy))
                continue;

            double share = split.getAmt();

            // participant owes paidBy

            participant.getBalSheet()
                    .adjustBal(paidBy, -share);

            paidBy.getBalSheet()
                    .adjustBal(participant, share);
        }
    }
}