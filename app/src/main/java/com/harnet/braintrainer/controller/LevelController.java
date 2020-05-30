package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.harnet.braintrainer.model.Level;

public class LevelController {
    private Level level;
    private LinearLayout levelView;
    private RulesController gameRulesController;

    public LevelController(LinearLayout levelView, RulesController gameRulesController) {
        this.levelView = levelView;
        this.gameRulesController = gameRulesController;
        this.level = new Level();
    }

    public Level getLevel() {
        return level;
    }

    public void addNextLevel(int rightAnswers, int wrongAnswers){
        if(gameRulesController.checkGameSessionResult(rightAnswers, wrongAnswers)){
            level.Up();
            updateLevelIcons();
        }
        // TODO works correctly add level.
                                // TODO Implement showing brains and speed up on next level
    }

    public void cancelLevel(){
        level.setLevelNum(0);
    }

    private void updateLevelIcons(){
        for(int i=0; i< level.getLevelNum() && i< levelView.getChildCount(); i++){
            final View subView = levelView.getChildAt(i);
            if (subView instanceof ImageView) {
                subView.setVisibility(View.VISIBLE);
            }
        }

    }
}
