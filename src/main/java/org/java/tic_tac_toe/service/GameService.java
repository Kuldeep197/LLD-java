package org.java.tic_tac_toe.service;

import org.java.tic_tac_toe.data.Board;
import org.java.tic_tac_toe.data.Game;
import org.java.tic_tac_toe.data.Player;
import org.java.tic_tac_toe.data.PlayerType;

import java.util.*;

public class GameService {

    private Deque<Player> playerDeque;

    public void startGame(){
        Board board = new Board(3,3);
        Player playerX = new Player("P1", PlayerType.X);
        Player playerO = new Player("P2", PlayerType.O);

        Game game = new Game(board, new ArrayList<>(List.of(playerO, playerX)));
        System.out.println(play(game));
    }

    public String play(Game game){
        System.out.println("Let's play the game ");
        Board board = game.getBoard();
        playerDeque = new LinkedList<>();
        playerDeque.addAll(game.getPlayers());
        boolean gameFinished = false;
        while(!gameFinished) {
            Player currentPlayer = playerDeque.removeFirst();
            printGameMatrix(board);
            System.out.println("Please enter x , y coords :  ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] values = input.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputCol = Integer.valueOf(values[1]);
            if (!isValidPosition(board, inputRow, inputCol)) {
                System.out.println("Please enter correct x , y coords :  ");
                input = scanner.nextLine();
                values = input.split(",");
                inputRow = Integer.valueOf(values[0]);
                inputCol = Integer.valueOf(values[1]);
                if (!isValidPosition(board, inputRow, inputCol)) {
                    System.out.println("Exiting game due to input failure ");
                    gameFinished = true;
                    continue;
                }
            }
            board.getMatrix()[inputRow][inputCol] = currentPlayer.getPlayerType().name().toCharArray()[0]; //TODO: place a map instead
            if(isWinner(board, currentPlayer, inputRow, inputCol) ){
                gameFinished = true;
                return "Done";
            }
            playerDeque.addLast(currentPlayer);
        }
        return "tied";
    }

    public static boolean isWinner(Board board, Player currentPlayer , int row, int col) {
        Character[][] matrix = board.getMatrix();
        boolean rowFlag = true;
        boolean colFlag = true;
        boolean diagonalFlag = true;
        boolean antiDiagonalFlag = true;
        for(int i=0;i<matrix.length;i++){
            if (matrix[i][col] == null || matrix[i][col] != currentPlayer.getPlayerType().name().toCharArray()[0]) {
                colFlag = false;
            }
        }

        for(int i=0;i<matrix[0].length;i++){
            if (matrix[row][i] == null || matrix[row][i] != currentPlayer.getPlayerType().name().toCharArray()[0]) {
                rowFlag = false;
            }
        }

        for(int i=0;i<matrix.length;i++){
            if (matrix[i][i] == null || matrix[i][i] != currentPlayer.getPlayerType().name().toCharArray()[0]) {
                diagonalFlag = false;
            }
        }

        for(int i=0, j=matrix.length - 1;i<matrix.length;i++,j--){
            if (matrix[i][j] == null || matrix[i][j] != currentPlayer.getPlayerType().name().toCharArray()[0]) {
                antiDiagonalFlag = false;
            }
        }
        if(rowFlag || colFlag || diagonalFlag || antiDiagonalFlag){
            System.out.println("Player "+ currentPlayer.getPlayerName() + "won the match");
        }
        return rowFlag || colFlag || diagonalFlag || antiDiagonalFlag;
    }

    public static void printGameMatrix(Board board) {
        Character[][] boardMatrix = board.getMatrix();
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                System.out.print(boardMatrix[i][j] + " |");
            }
            System.out.println();
        }
    }

    public static boolean isValidPosition(Board board, int row, int col){
        return col < board.getCol() && row < board.getRow() && row >= 0 && col >= 0 &&
                board.getMatrix()[row][col] == null;
    }
}
