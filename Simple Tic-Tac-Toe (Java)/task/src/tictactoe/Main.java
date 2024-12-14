package tictactoe;

import java.util.Scanner;
import static java.lang.Math.abs;

public class Main {
    final static String cap = "---------";
    static Coordinate userCoord = new Coordinate();

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        char[][] board = convertInputStringToCharArray("_________");
        printBoard(board);
        int move = 0;
        while (checkGameState(board)) {
            char turn = 'O';
            if (move % 2 == 0) {
                turn = 'X';
            }
            String str = userInput.nextLine();
            userCoord.setCoordinate(str);
            if (processUserInput(board, userCoord, turn)) {
                printBoard(board);
                move++;
            }
        }
    }

    public static char[][] convertInputStringToCharArray(String input) {
        if (input.length() == 9) {
            char[][] board = new char[3][3];
            char[] chars = input.toCharArray();
            for (int i = 0; i < 3; i++) {
                System.arraycopy(chars, i * 3, board[i], 0, 3);
            }
            return board;
        } else {
            throw new IllegalArgumentException("Input must be exactly 9 characters long.");
        }

    }

    public static void printBoard(char[][] board) {
        System.out.println(cap);
        for (char[] row : board) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
        System.out.println(cap);
    }

    //  Returns true if game is still going; Returns false if game is over.
    public static boolean checkGameState(char[][] board) {
        int countX = 0;
        int countO = 0;
        int countEmpty = 0;
        int countWinners = 0;
        char winner = ' ';

        // gets count of each token
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == 'X') {
                    countX++;
                } else if (cell == 'O') {
                    countO++;
                } else {
                    countEmpty++;
                }
            }
        }

        // Checks for even turn count
        if (abs(countX - countO) >= 2) {
            System.out.println("Impossible");
            return false;
        }

        // Checks for winners
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] != '_') {
                    countWinners++;
                    winner = board[i][0];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] != '_') {
                    countWinners++;
                    winner = board[0][i];
                }
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] != '_') {
                countWinners++;
                winner = board[0][0];
            }
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] != '_') {
                countWinners++;
                winner = board[0][2];
            }
        }

        if (countWinners == 0 && countEmpty > 0) {
//            System.out.println("Game not finished");
            return true;
        }
        if (countWinners == 1) {
            System.out.println(winner + " wins");
            return false;
        } else if (countWinners > 1) {
            System.out.println("Impossible");
            return false;
        }
        if (countWinners == 0 && countEmpty == 0) {
            System.out.println("Draw");
            return false;
        }
        return false;
    }

    public static boolean processUserInput(char[][] board, Coordinate userCoord, char turn) {
        if (userCoord.getX() < 1 || userCoord.getX() > 3 || userCoord.getY() < 1 || userCoord.getY() > 3) {
            return false;
        }
        if (board[userCoord.getX() - 1][userCoord.getY() - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return false; // Exit this method without making any changes
        }
        // Place the mark ('X') in the correct cell
        board[userCoord.getX() - 1][userCoord.getY() - 1] = turn;
        return true;
    }


    static class Coordinate {
        private int x;
        private int y;

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setCoordinate(String str) {
            String[] parts = str.split(" ");

            int tempX = 0;
            int tempY = 0;
            try {
                tempX = Integer.parseInt(parts[0]);
                tempY = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }

            if (tempX < 1 || tempX > 3 || tempY < 1 || tempY > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                this.x = tempX;
                this.y = tempY;
            }
        }
    }
}

























