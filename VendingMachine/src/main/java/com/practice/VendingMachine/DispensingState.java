package com.practice.VendingMachine;

public class DispensingState extends VendingMachineState {
    private Shelf shelf;

    public DispensingState(Shelf shelf) {
        this.shelf = shelf;
    }

    @Override
    public void insertCoin(VendingMachineSystem vm, Coin coin) {
        System.out.println("Currently dispensing, cannot insert coins.");
    }

    @Override
    public void selectItem(VendingMachineSystem vm, int shelfCode) {
        System.out.println("Already dispensing an item.");
    }

    @Override
    public void dispenseItem(VendingMachineSystem vm) {
        if (shelf.isAvailable()) {
            Item item = shelf.items.get(0);
            shelf.removeFromShelf();
            vm.setState(new IdleState());
            System.out.println("Dispensed: " + item.getType());
        } else {
            System.out.println("Item not available.");
            vm.setState(new IdleState());
        }
    }

    @Override
    public void refund(VendingMachineSystem vm) {
        System.out.println("Cannot refund while dispensing.");
    }
}
