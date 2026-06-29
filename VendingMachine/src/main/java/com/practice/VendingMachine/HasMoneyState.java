package com.practice.VendingMachine;

public class HasMoneyState extends VendingMachineState {

    @Override
    public void insertCoin(VendingMachineSystem vm, Coin coin) {
        vm.getCoinList().add(coin);
        System.out.println("Coin inserted: " + coin.getValue());
    }

    @Override
    public void selectItem(VendingMachineSystem vm, int shelfCode) {
        Shelf shelf = vm.getShelves().stream()
                        .filter(s -> s.code == shelfCode)
                        .findFirst()
                        .orElse(null);

        if (shelf != null && shelf.isAvailable()) {
            Item item = shelf.items.get(0);
            int totalMoney = vm.getCoinList().stream().mapToInt(Coin::getValue).sum();
            if (totalMoney >= item.getPrice()) {
                vm.setState(new DispensingState(shelf));
            } else {
                System.out.println("Not enough money. Please insert more coins.");
            }
        } else {
            System.out.println("Invalid shelf or item not available.");
        }
    }

    @Override
    public void dispenseItem(VendingMachineSystem vm) {
        System.out.println("Select an item first.");
    }

    @Override
    public void refund(VendingMachineSystem vm) {
        vm.getCoinList().clear();
        vm.setState(new IdleState());
        System.out.println("Coins refunded.");
    }
}
