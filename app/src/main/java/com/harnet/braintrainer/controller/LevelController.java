package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.harnet.braintrainer.model.Level;

public class LevelController {
    private static final String TAG = "LevelController";
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

    public boolean addNextLevel(int rightAnswers, int wrongAnswers){
        if(gameRulesController.checkGameSessionResult(rightAnswers, wrongAnswers)){
            level.up();
            updateLevelIcons();
            return true;
        }
        return false;
    }

    public void resetLevel(){
        level.setLevelNum(0);
    }

    public void upMultipl(){
        level.setMultiplicator(level.getMultiplicator() + 1);
    }

    public void updateLevelIcons(){
        for(int i=0; i< level.getLevelNum() && i< levelView.getChildCount(); i++){
            final View subView = levelView.getChildAt(i);
            if (subView instanceof ImageView) {
                subView.setVisibility(View.VISIBLE);
            }
        }

    }

    public void resetLevelIcons(){
        for(int i=0; i< levelView.getChildCount(); i++){
            final View subView = levelView.getChildAt(i);
            if (subView instanceof ImageView) {
                subView.setVisibility(View.INVISIBLE);
            }
        }

    }

    public void resetLevelBounds(){
        level.setMinBound(level.getMIN_BOUNDS_DEFAULT());
        level.setMaxBound(level.getMAX_BOUNDS_DEFAULT());
    }
}
