package com.practice.SnakeAndLaddersGame;

public class Game {
    public static void main(String[] args) {
        Board board = new Board(1, 100, 2);
        board.addPlayer(new Player("Alice"));
        board.addPlayer(new Player("Bob"));

        board.initializeBoard(5, 5);
        board.startGame();
    }
}