package org.java.snake_ladder_game.data;

public class Board {

    private int[] board;

    public Board(int i){
        board = new int[i];
    }

    public int[] getBoard(){
        return this.board;
    }
}
