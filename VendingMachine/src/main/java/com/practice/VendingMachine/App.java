package com.practice.VendingMachine;

import java.util.List;

public class App {
        public static void main(String[] args) {
        	
            VendingMachineSystem vm = new VendingMachineSystem();
            
            vm.stockShelves(5); // stock 5 items per shelf

            vm.inputCoins(List.of(50, 100)); // insert coins

            System.out.println("Shelves stocked: " + vm.getShelves().size());
            System.out.println("Coins inserted: " + vm.getCoinList().size());
            
            
        }

   
}
