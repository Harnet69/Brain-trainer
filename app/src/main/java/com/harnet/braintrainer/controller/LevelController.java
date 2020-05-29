package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LevelController {
    private int currentLevel;
    private LinearLayout levelView;
    private RulesController gameRulesController;

    public LevelController(LinearLayout levelView, RulesController gameRulesController) {
        this.levelView = levelView;
        this.gameRulesController = gameRulesController;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void addNextLevel(int rightAnswers, int wrongAnswers){
        if(gameRulesController.checkGameSessionResult(rightAnswers, wrongAnswers)){
            currentLevel+=1;
            System.out.println("Player won level");
            updateLevelIcons();
        }
        System.out.println("Level" + currentLevel); // TODO works correctly add level.
                                // TODO Implement showing brains and speed up on next level
    }

    public void cancelLevel(){
        currentLevel = 0;
    }

    private void updateLevelIcons(){
        for(int i=0; i< currentLevel && i< levelView.getChildCount(); i++){
            final View subView = levelView.getChildAt(i);
            if (subView instanceof ImageView) {
                subView.setVisibility(View.VISIBLE);
            }
        }

    }
}
