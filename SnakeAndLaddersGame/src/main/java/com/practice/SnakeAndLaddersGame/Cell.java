package com.practice.SnakeAndLaddersGame;

public class Cell {
    private int val;
    private Jump jump;

    public Cell(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }
}