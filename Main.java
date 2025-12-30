package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] fieldArray = new char[3][3];

        // 1. Read the initial 9-character board state from the tester
        // Example input: _XXOO_OX_
        String inputState = scanner.next();

        // 2. Build the board from that string
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldArray[i][j] = inputState.charAt(i * 3 + j);
            }
        }

        // 3. Print the initial board (This is the FIRST field the test looks for)
        printBoard(fieldArray);

        // 4. Make exactly ONE move for 'X'
        // This method will handle the coordinates and print the SECOND field
        makeMove(fieldArray, 'X', scanner);
    }

    public static void makeMove(char[][] board, char symbol, Scanner scanner) {
        while (true) {
            // Some testers require the prompt "Enter the coordinates: "
            // If yours doesn't, you can leave it out, but it's safer to include.
            System.out.print("Enter the coordinates: ");

            if (!scanner.hasNext()) break;
            String xMoveRow = scanner.next();

            if (!scanner.hasNext()) break;
            String xMoveCol = scanner.next();

            if (!inputIsNumeric(xMoveRow) || !inputIsNumeric(xMoveCol)) {
                System.out.println("You should enter numbers!");
            } else {
                int row = Integer.parseInt(xMoveRow) - 1;
                int col = Integer.parseInt(xMoveCol) - 1;

                if (!inputIsInArrayRange(row) || !inputIsInArrayRange(col)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!inputIsNotOccupied(board, row, col)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    board[row][col] = symbol;
                    printBoard(board);
                    break; // Move finished
                }
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                // Replacing underscores with spaces if necessary for visual consistency
                char displayChar = (board[i][j] == '_') ? ' ' : board[i][j];
                System.out.print(displayChar + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean inputIsNumeric(String str) {
        return str.matches("-?\\d+");
    }

    public static boolean inputIsInArrayRange(int position) {
        return position >= 0 && position <= 2;
    }

    public static boolean inputIsNotOccupied(char[][] field, int row, int col) {
        // Checking for both space and underscore to be safe
        return field[row][col] == ' ' || field[row][col] == '_';
    }
}