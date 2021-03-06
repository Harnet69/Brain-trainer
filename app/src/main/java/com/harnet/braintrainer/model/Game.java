package com.harnet.braintrainer.model;

public class Game {
    private static Game instance = null;
    private boolean isGame;

    private Game() {
    }

    public static Game getInstance() {
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public boolean isGame() {
        return isGame;
    }

    public void setGame(boolean game) {
        isGame = game;
    }
}
