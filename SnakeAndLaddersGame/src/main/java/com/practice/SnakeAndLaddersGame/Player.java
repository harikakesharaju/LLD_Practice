package com.practice.SnakeAndLaddersGame;

public class Player {
    private String name;
    private int currentCell;

    public Player(String name) {
        this.name = name;
        this.currentCell = 0;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCell() {
        return currentCell;
    }

    public void makeMove(int diceRoll, Cell[] cells, int boardSize) {
        int nextPos = currentCell + diceRoll;
        if (nextPos > boardSize) {
            System.out.println(name + " rolled " + diceRoll + " but stays at " + currentCell);
            return;
        }

        currentCell = nextPos;
        System.out.println(name + " rolled " + diceRoll + " and moved to " + currentCell);

        if (cells[currentCell].getJump() != null) {
            Jump jump = cells[currentCell].getJump();
            if (jump.getStartCell() == currentCell) {
                currentCell = jump.getEndCell();
                System.out.println(name + " encountered a jump! Moved to " + currentCell);
            }
        }
    }
}