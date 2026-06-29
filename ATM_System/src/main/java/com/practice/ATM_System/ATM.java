package com.practice.ATM_System;

import com.practice.ATM_System.ATM_States.ATMState;

public class ATM {
    private int balance;
    private int noof2k;
    private int noof500;
    private int noof200;
    private ATMState state;

    public ATM(int a, int b, int c, ATMState st) {
        this.noof200 = a;
        this.noof500 = b;
        this.noof2k = c;
        this.state = st;
        this.balance = 200 * a + 500 * b + 2000 * c;
    }

    public void setBal(int b) {
        this.balance = b;
    }

    public int getBal() {
        return this.balance;
    }

    public int getNoof2k() {
        return noof2k;
    }

    public void setNoof2k(int noof2k) {
        this.noof2k = noof2k;
    }

    public int getNoof500() {
        return noof500;
    }

    public void setNoof500(int noof500) {
        this.noof500 = noof500;
    }

    public int getNoof200() {
        return noof200;
    }

    public void setNoof200(int noof200) {
        this.noof200 = noof200;
    }

    public ATMState getState() {
        return state;
    }

    public void setState(ATMState state) {
        this.state = state;
    }
}
