package org.java.snake_ladder_game;

import org.java.snake_ladder_game.service.GameService;

public class Launcher {

    public static void main(String[] args) {
        GameService gameService = new GameService();
        gameService.startGame();
    }
}
