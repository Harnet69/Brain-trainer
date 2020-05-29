package com.harnet.braintrainer.controller;

import android.widget.LinearLayout;

public class LevelController {
    private int currentLevel;
    private LinearLayout levelView;
    private GameRulesController gameRulesController;

    public LevelController(LinearLayout levelView) {
        this.levelView = levelView;
        gameRulesController = new GameRulesController();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void addNextLevel(int rightAnswers, int wrongAnswers){
        if(gameRulesController.checkGameSessionResult(rightAnswers, wrongAnswers)){
            currentLevel+=1;
            System.out.println("Player won level");
        }
        System.out.println("Level" + currentLevel); // TODO works correctly add level.
                                // TODO Implement showing brains and speed up on next level
    }

    public void cancelLevel(){
        currentLevel = 0;
    }
}
