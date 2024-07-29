package org.java.snake_ladder_game.data;

public class Player {

    private String playerName;
    private int position;

    public Player(String playerName){
        this.playerName = playerName;
        this.position = 0;
    }

    public int getPosition(){
        return this.position;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public void setPosition(int pos){
        this.position = pos;
    }
}
