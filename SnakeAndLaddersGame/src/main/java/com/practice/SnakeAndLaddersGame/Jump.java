package com.practice.SnakeAndLaddersGame;

public class Jump {
    private int startCell;
    private int endCell;

    public Jump(int start, int end) {
        this.startCell = start;
        this.endCell = end;
    }

    public int getStartCell() {
        return startCell;
    }

    public int getEndCell() {
        return endCell;
    }
}