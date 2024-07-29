package org.java.tic_tac_toe.data;

public class Board {
    private Character[][] matrix;
    private int row;
    private int col;

    public Board(int row, int col){
        this.row = row;
        this.col = col;
        this.matrix = new Character[row][col];
    }

    public Character[][] getMatrix(){
        return this.matrix;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
