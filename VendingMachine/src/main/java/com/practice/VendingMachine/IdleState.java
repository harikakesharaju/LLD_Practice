package com.practice.VendingMachine;

public class IdleState extends VendingMachineState {

    @Override
    public void insertCoin(VendingMachineSystem vm, Coin coin) {
        vm.getCoinList().add(coin);
        vm.setState(new HasMoneyState());
        System.out.println("Coin inserted: " + coin.getValue());
    }

    @Override
    public void selectItem(VendingMachineSystem vm, int shelfCode) {
        System.out.println("Insert coins first!");
    }

    @Override
    public void dispenseItem(VendingMachineSystem vm) {
        System.out.println("No coins inserted.");
    }

    @Override
    public void refund(VendingMachineSystem vm) {
        System.out.println("No coins to refund.");
    }
}
