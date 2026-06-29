package com.practice.CarRentalSystem;

public class Payment {
    private Bill bill;

    public Payment(Bill bill) {
        this.bill = bill;
    }

    public void pay() {
        bill.markPaid();
        System.out.println("Payment successful. Amount: " + bill.getTotamount());
    }
}
