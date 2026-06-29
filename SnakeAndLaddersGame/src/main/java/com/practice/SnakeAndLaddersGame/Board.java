package com.practice.SnakeAndLaddersGame;

import java.util.*;

public class Board {
    private Cell[] cells;
    private int size;
    private int noOfDice;
    private List<Player> players;
    private Status status;

    public Board(int dice, int n, int pl) {
        this.noOfDice = dice;
        this.size = n;
        this.players = new ArrayList<>();
        this.cells = new Cell[n + 1]; // 1-based indexing
        for (int i = 0; i <= n; i++) {
            cells[i] = new Cell(i);
        }
        this.status = Status.IN_PROGRESS;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void initializeBoard(int noOfSnakes, int noOfLadders) {
        Random rand = new Random();

        // Add snakes
        for (int i = 0; i < noOfSnakes; i++) {
            int start = rand.nextInt(size - 10) + 10;
            int end = rand.nextInt(start - 1) + 1;
            cells[start].setJump(new Jump(start, end));
        }

        // Add ladders
        for (int i = 0; i < noOfLadders; i++) {
            int start = rand.nextInt(size - 10) + 1;
            int end = rand.nextInt(size - start) + start + 1;
            cells[start].setJump(new Jump(start, end));
        }
    }

    public void startGame() {
        Random rand = new Random();
        while (status == Status.IN_PROGRESS) {
            for (Player p : players) {
                int diceRoll = 0;
                for (int i = 0; i < noOfDice; i++) {
                    diceRoll += rand.nextInt(6) + 1;
                }
                p.makeMove(diceRoll, cells, size);

                if (p.getCurrentCell() == size) {
                    System.out.println(p.getName() + " wins!");
                    status = Status.WIN;
                    return;
                }
            }
        }
    }
}