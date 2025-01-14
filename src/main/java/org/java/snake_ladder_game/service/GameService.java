package org.java.snake_ladder_game.service;

import org.java.snake_ladder_game.data.Board;
import org.java.snake_ladder_game.data.Game;
import org.java.snake_ladder_game.data.Player;

import java.util.*;

public class GameService {

    private Deque<Player> playerDeque;
    private Map<Integer, List<Player>> posToPlayerMap;
    private static Scanner scanner;
    private static Random random;

    static {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void startGame(){
        posToPlayerMap = new HashMap<>();
        System.out.println("Game is started ...");
        System.out.println("Please Enter the no. of players :");
        Board board = new Board(101);
        List<Player> players = new ArrayList<>();
        int input = scanner.nextInt();
        scanner.nextLine();
        while(input !=0 ){
            System.out.println("Please enter the player " + input + " name : ");
            String name = scanner.nextLine();
            Player player = new Player(name);
            players.add(player);
            posToPlayerMap.putIfAbsent(player.getPosition(), new ArrayList<>());
            posToPlayerMap.get(player.getPosition()).add(player);
            input--;
        }

        populateBoardWithSnakeAndLadders(board);
        Game game = new Game(board, players);
        String winner = play(game);
        if(winner != null ){
            System.out.println("Player " + winner + " won the game ");
        }
        else {
            System.out.println("Game Ended without result");
        }
    }

    public String play(Game game){
        playerDeque  = new LinkedList<>();
        playerDeque.addAll(game.getPlayers());
        while(true){
            Player player = playerDeque.removeFirst();
            int currPos = player.getPosition();
            printGameBoard(game.getBoard().getBoard());
            System.out.println(" " + player.getPlayerName() + " Please press enter to roll dice ");
            scanner.nextLine();
            System.out.println("Rolling dice...");
            int diceVal = rollDice(player);
            System.out.println("you got " + diceVal);
            int finalPos = getFinalPos(player, diceVal);
            if(isWinner(player, finalPos, game.getBoard())){
                return player.getPlayerName();
            }
            playerDeque.addLast(player);
            if(currPos != player.getPosition()) {
                posToPlayerMap.putIfAbsent(player.getPosition(), new ArrayList<>());
                posToPlayerMap.get(player.getPosition()).add(player);
                posToPlayerMap.get(currPos).remove(player);
            }
        }
    }

    public Integer getFinalPos(Player player, int diceVal){
        int finalPos = player.getPosition() + diceVal;
        return finalPos;
    }

    public static boolean isWinner(Player player, int finalPos, Board board){
         System.out.println("Your initial position : " + finalPos);
        int[] gameBoard = board.getBoard();
        if(finalPos > 100){
            System.out.println("Oops you got " + finalPos);
        }
        else if(gameBoard[finalPos] < 0) {
            System.out.println(" you got bitten by snake and you go to go down by " + gameBoard[finalPos]);
            player.setPosition(finalPos + gameBoard[finalPos]);
            return isWinner(player, player.getPosition(), board);
        }
        else if(gameBoard[finalPos] > 0) {
            System.out.println(" you got a ladder to climb and you will climb positions " + gameBoard[finalPos]);
            player.setPosition(finalPos + gameBoard[finalPos]);
            return isWinner(player, player.getPosition(), board);
        }
        else if(finalPos == 100){
            player.setPosition(finalPos);
            return true;
        }
        else {
            player.setPosition(finalPos);
        }
        System.out.println("Your final position : " + player.getPosition());
        return false;
    }

    public static int rollDice(Player player){
        int newPos = random.nextInt(1,6);
        int finalDice = 0;
        if(newPos == 6){
            int count = 1;
            finalDice += newPos;
            while(newPos == 6){
                System.out.println(" " + player.getPlayerName() + "Please press enter to roll dice again as you get 6");
                scanner.nextLine();
                newPos = random.nextInt(1,6);
                finalDice += newPos;
                count++;
                if(count == 3){
                    System.out.println(" " + player.getPlayerName() + "Oops you got 3 sixes in a row !!!! gotta roll dice again");
                    rollDice(player);
                }
            }
        }
        else {
            finalDice = newPos;
        }
        return finalDice;
    }

    public void populateBoardWithSnakeAndLadders(Board board){
        int[] gameBoard = board.getBoard();
        int ladders = random.nextInt(1,10);
        int snakes = random.nextInt(1,10);
        for(int i=0;i<snakes;i++){
            int pos = random.nextInt(1,100);
            int snakeBitePos = random.nextInt(1,pos);
            gameBoard[pos] = -snakeBitePos;
            System.out.println("snake at " + pos  + "with value " + snakeBitePos);
        }

        for(int i=0;i<ladders;i++){
            int pos = random.nextInt(1,100);
            while(gameBoard[pos] < 0){
                pos = random.nextInt(1,100);
            }
            int ladderPos = random.nextInt(1,100-pos);
            gameBoard[pos] = ladderPos;
            System.out.println("Ladder at " + pos  + "with value " + ladderPos);
        }

        printGameBoard(gameBoard);
    }

    private void printGameBoard(int[] gameBoard) {
        System.out.println("Printing the board ...");
        int i=1;
        while(i < 100) {
            int count = 0;
            while(count < 10){
                String ch;
                if(gameBoard[i] < 0 )
                    ch = "Sn";
                else if(posToPlayerMap.containsKey(i) && posToPlayerMap.get(i) != null && posToPlayerMap.get(i).size() > 0)
                    ch = "P";
                else if(gameBoard[i] > 0)
                    ch = "La";
                else
                    ch = Integer.toString(i);
                System.out.print(" | " + (ch));
                count++;
                i++;
            }
            System.out.println();
        }

        for(Map.Entry<Integer, List<Player>> entry : posToPlayerMap.entrySet()){
            if(entry.getValue() != null && entry.getValue().size() > 0){
                System.out.println("Players at position " + entry.getKey() + " " );
                for(Player player : entry.getValue()){
                    System.out.println(player.getPlayerName());
                }
            }
        }
    }
}
