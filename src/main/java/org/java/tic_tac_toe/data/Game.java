package org.java.tic_tac_toe.data;

import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;

    public Game(Board board, List<Player> players){
        this.board = board;
        this.players = players;
    }

    public Board getBoard(){
        return this.board;
    }

    public List<Player> getPlayers(){
        return this.players;
    }




}
