package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static int fieldRows = 3;
    static int fieldColumns = 3;

    public static void main(String[] args) {
        char[][] playingField = new char[fieldRows][fieldColumns];
        Random random = new Random();
        boolean isPlayerPlayingAsX = random.nextBoolean();
        boolean isGameGoing = true;
        int gameTurn = 1;
        
        if (isPlayerPlayingAsX) {
            System.out.println("You're playing as X");
        }
        else {
            System.out.println("You're playing as O");
        }
        
        // Filling game field with '-' symbol
        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < fieldColumns; j++) {
                playingField[i][j] = '-';
            }
        }
        
        while (isGameGoing) {
            printGameField(playingField, fieldRows, fieldColumns);
            
            isGameGoing = false;
            
            // If there is at least one '-' then game is still going
            for (int i = 0; i < fieldRows; i++) {
                for (int j = 0; j < fieldColumns; j++) {
                    if (playingField[i][j] == '-') {
                        isGameGoing = true;
                    }
                }
            }

            if (isGameGoing) {
                isGameGoing = !gameEnded(playingField, fieldRows, fieldColumns);
            }
            
            if (isGameGoing) {
                System.out.println("Turn #" + gameTurn);
                
                if (isPlayerPlayingAsX) {
                    if (gameTurn % 2 == 1) {
                        makePlayerMove(playingField, isPlayerPlayingAsX);
                    }
                    else {
                        makeComputerMove(playingField, isPlayerPlayingAsX);
                    }
                }
                else {
                    if (gameTurn % 2 == 0) {
                        makePlayerMove(playingField, isPlayerPlayingAsX);
                    }
                    else {
                        makeComputerMove(playingField, isPlayerPlayingAsX);
                    }
                }
                gameTurn++;
            }
            else {
                System.out.println("Game ended");
            }
        }
    }

    private static boolean gameEnded(char[][] playingField, int fieldRows, int fieldColumns) {
        // TODO: Add implementation for case: "/"
        int countSymbols = 1;

        for (int i = 1; i < fieldRows; i++) {
            for (int j = 0; j < fieldColumns; j++) {
                if (playingField[j][i] != '-' && playingField[j][i - 1] == playingField[j][i]) {
                    countSymbols++;
                }
            }
        }
        if (countSymbols == 3) {
            return true;
        }
        countSymbols = 1;

        for (int i = 1; i < fieldRows; i++) {
            for (int j = 0; j < fieldColumns; j++) {
                if (playingField[i][j] != '-' && playingField[i - 1][j] == playingField[i][j]) {
                    countSymbols++;
                }
            }
        }
        if (countSymbols == 3) {
            return true;
        }
        countSymbols = 1;

        int i = 1;
        int j = 1;

        while (i < fieldRows) {
            if (playingField[i][j] != '-' && playingField[i][j] == playingField[i - 1][j - 1]) {
                countSymbols++;
            }
            i++;
            j++;
        }
        if (countSymbols == 3) {
            return true;
        }

        return false;
    }

    private static void printGameField(char[][] playingField, int fieldRows, int fieldColumns) {
        System.out.println("Current game field: ");
        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < fieldColumns; j++) {
                System.out.print(playingField[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void makeComputerMove(char[][] playingField, boolean isPlayerPlayingAsX) {
        // TODO: Think of a new implementation
        Random random = new Random();
        char computerSymbol;
        boolean computerMoved = false;

        if (isPlayerPlayingAsX) {
            computerSymbol = 'O';
        }
        else {
            computerSymbol = 'X';
        }

        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < fieldColumns; j++) {
                if (playingField[i][j] == '-') {
                    computerMoved = true;
                    playingField[i][j] = computerSymbol;
                    System.out.println("Computer marks " + computerSymbol + " at [" + (i + 1) + "][" + (j + 1) + "]");
                    break;
                }
            }
            if (computerMoved) {
                break;
            }
        }
    }

    private static void makePlayerMove(char[][] playingField, boolean isPlayerPlayingAsX) {
        Scanner scanner = new Scanner(System.in);
        int row;
        int column;
        char playerSymbol;
        System.out.println("Enter row of cell you want to mark: ");
        row = scanner.nextInt();
        System.out.println("Enter column of cell you want to mark: ");
        column = scanner.nextInt();

        boolean isMoveLegal = checkIfMoveIsLegal(playingField, row, column);

        if (isPlayerPlayingAsX) {
            playerSymbol = 'X';
        }
        else {
            playerSymbol = 'O';
        }

        if (!isMoveLegal) {
            makePlayerMove(playingField, isPlayerPlayingAsX);
        }
        else {
            System.out.println("You mark " + playerSymbol + " at [" + row + "][" + column + "]");
            playingField[row - 1][column - 1] = playerSymbol;
        }
    }

    private static boolean checkIfMoveIsLegal(char[][] playingField, int row, int column) {
        if (row > fieldRows) {
            System.out.println("This row does not exist in the array! Try again!");
            return false;
        }
        if (column > fieldColumns) {
            System.out.println("This column does not exist in the array! Try again!");
            return false;
        }
        if (playingField[row - 1][column - 1] != '-') {
            System.out.println("This cell is already marked! Try again!");
            return false;
        }

        return true;
    }

}