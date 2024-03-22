import java.util.Scanner;
public class TicTacToe {

    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String [][] board = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //variables
        String playerOne = "";
        String playerTwo = "";
        boolean gameDone = false;
        boolean done = false;
        boolean doneRound = false;
        boolean donePlayerOne = false;
        boolean donePlayerTwo = false;
        int playerOneMoveRow = -1;
        int playerOneMoveCol = -1;
        int playerTwoMoveRow = -1;
        int playerTwoMoveCol = -1;
        String temp;

        //Players choose to be X or O and the cleared board is displayed
        while (!gameDone) {
            done = false;
            doneRound = false;
            while (!done) {
                temp = InputHelper.getNonZeroLenString(scan, "Player 1, choose X or O");
                if (temp.equalsIgnoreCase("X")) {
                    playerOne = "X";
                    playerTwo = "O";
                    done = true;
                } else if (temp.equalsIgnoreCase("O")) {
                    playerOne = "O";
                    playerTwo = "X";
                    done = true;
                } else {
                    System.out.println("Invalid");
                }
            }
            clearBoard();
            displayBoard();

            //Players make their moves and boards are updated to have the location on the board as the player's chosen value(X or O)
            while (!doneRound) {
                donePlayerOne = false;
                donePlayerTwo = false;
                while (!donePlayerOne){
                    playerOneMoveRow = InputHelper.getRangedInt(scan, "Player One, pick a row for your move", 1, 3);
                    playerOneMoveCol = InputHelper.getRangedInt(scan, "Player One, pick a column for your move", 1, 3);
                    if (isValidMove(playerOneMoveRow - 1, playerOneMoveCol - 1)) {
                        donePlayerOne = true;
                    } else {
                        System.out.println("That move is already taken");
                    }
                }
                board[playerOneMoveRow -1][playerOneMoveCol -1] = playerOne;
                displayBoard();
                if (isWin(playerOne) || isTie()) {
                    doneRound = true;
                    break;
                }
                while (!donePlayerTwo){
                    playerTwoMoveRow = InputHelper.getRangedInt(scan, "Player Two, pick a row for your move", 1, 3);
                    playerTwoMoveCol = InputHelper.getRangedInt(scan, "Player Two, pick a column for your move", 1, 3);
                    if (isValidMove(playerTwoMoveRow - 1, playerTwoMoveCol - 1)) {
                        donePlayerTwo = true;
                    } else {
                        System.out.println("That move is already taken");
                    }
                }
                board[playerTwoMoveRow -1][playerTwoMoveCol -1] = playerTwo;
                displayBoard();
                if (isWin(playerTwo) || isTie()) {
                    doneRound = true;
                    break;
                }
            }

            if (isWin(playerOne)) {
                System.out.println("Player One Wins!");
            } else if (isWin(playerTwo)) {
                System.out.println("Player Two Wins!");
            } else if (isTie()) {
                System.out.println("You Tied!");
            }

            gameDone = InputHelper.getYNConfirm(scan, "Would you like to play again?(Y/N)");
        }
    }

    private static void clearBoard(){
        for (int r = 0; r<board.length; r++) {
            for (int c = 0; c<board[0].length; c++) {
                board[r][c] = "-";
            }
        }
    }

    private static void displayBoard(){
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col){
        boolean valid = false;
        if (board[row][col] == "-") {
            valid = true;
        }
        return valid;
    }

    private static boolean isWin(String player){
        boolean win = false;
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            win = true;
        }
        return win;
    }

    private static boolean isColWin(String player){
        boolean colWin = false;
        int counter = 0;
        for (int j = 0; j < board[0].length; j++) {
            counter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[j][i].equals(player)) {
                    counter++;
                }
                if (counter == 3) {
                    colWin = true;
                    break;
                }
            }
        }
        return colWin;
    }

    private static boolean isRowWin(String player){
        boolean rowWin = false;
        int counter = 0;
        for (int j = 0; j < board[0].length; j++) {
            counter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j].equals(player)) {
                    counter++;
                }
                if (counter == 3) {
                    rowWin = true;
                    break;
                }
            }
        }
        return rowWin;
    }

    private static boolean isDiagonalWin(String player){
        boolean diagWin = false;
        if (board[0][0].equals(player) && board[2][2].equals(player) && board[1][1].equals(player)) {
            diagWin = true;
        } else if (board[2][0].equals(player) && board[0][2].equals(player) && board[1][1].equals(player)) {
            diagWin = true;
        }
        return diagWin;
    }

    private static boolean isTie(){
        boolean tie = false;
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals("-")) {
                    counter++;
                    break;
                }
            }
        }
        if (counter == 0) {
            tie = true;
        }
        return tie;
    }
}