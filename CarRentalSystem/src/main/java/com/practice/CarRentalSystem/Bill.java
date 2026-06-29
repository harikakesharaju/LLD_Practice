package com.practice.CarRentalSystem;

public class Bill {
    private Request req;
    private boolean isPaid;
    private double totamount;

    public Bill(Request req, double totamount) {
        this.req = req;
        this.totamount = totamount;
        this.isPaid = false;
    }

    public double getTotamount() { return totamount; }
    public boolean isPaid() { return isPaid; }
    public void markPaid() { this.isPaid = true; }
}
