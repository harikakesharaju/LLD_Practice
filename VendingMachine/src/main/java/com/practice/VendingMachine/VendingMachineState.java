package com.practice.VendingMachine;

public abstract class VendingMachineState {
    
    public abstract void insertCoin(VendingMachineSystem vm, Coin coin);
    
    public abstract void selectItem(VendingMachineSystem vm, int shelfCode);
    
    public abstract void dispenseItem(VendingMachineSystem vm);
    
    public abstract void refund(VendingMachineSystem vm);
}
