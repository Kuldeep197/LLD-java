package org.java.tic_tac_toe.data;

public class Player {

    private String playerName;
    private PlayerType playerType;

    public Player(String name, PlayerType playerType){
        this.playerName = name;
        this.playerType = playerType;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public PlayerType getPlayerType(){
        return this.playerType;
    }
}
