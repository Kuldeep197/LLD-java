package org.java.tic_tac_toe;

import org.java.tic_tac_toe.service.GameService;

public class Launcher {

    public static void main(String[] args) {
        GameService gameService = new GameService();
        gameService.startGame();
    }
}
