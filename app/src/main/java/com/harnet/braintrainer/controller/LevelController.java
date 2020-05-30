package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.model.Level;

public class LevelController {
    private static final String TAG = "LevelController";
    private int[] levelGridImages = {R.drawable.brain_00_ico, R.drawable.brain_01_ico, R.drawable.brain_02_ico, R.drawable.brain_03_ico, R.drawable.brain_04_ico, R.drawable.brain_05_ico}; // icons for a levels icons grid
    private int[] levelImages =      {R.drawable.brain_00,    R.drawable.brain_01,     R.drawable.brain_02,     R.drawable.brain_03,     R.drawable.brain_04,     R.drawable.brain_05}; // image for gear spinner

    private Level level;
    private LinearLayout levelView;
    private RulesController gameRulesController;
    private TextView levelNumtextView;

    public LevelController(LinearLayout levelView, RulesController gameRulesController, TextView levelNumtextView) {
        this.levelView = levelView;
        this.gameRulesController = gameRulesController;
        this.levelNumtextView = levelNumtextView;
        this.level = new Level(0);
    }

    public int[] getLevelImages() {
        return levelImages;
    }

    public Level getLevel() {
        return level;
    }

    public boolean addNextLevel(int rightAnswers, int wrongAnswers) {
        if (gameRulesController.checkGameSessionResult(rightAnswers, wrongAnswers)) {
            level.up();
            updateLevelIcons();
            return true;
        }
        return false;
    }

    public void resetLevel() {
        level.setLevelNum(0);
    }

    public void upMultipl() {
        level.setMultiplicator(level.getMultiplicator() + 1);
    }

    public void updateLevelIcons() {
        for (int i = 0; i < level.getLevelNum() && i < levelView.getChildCount(); i++) {
            final View subView = levelView.getChildAt(i);
            if (subView instanceof ImageView) {
                subView.setVisibility(View.VISIBLE);
            }
        }
    }

    // fill level icons grid by icons
    public void fillLevelsByIcons() {
        for (int i = 0; i < levelView.getChildCount(); i++) {
            final View subView = levelView.getChildAt(i);
            if (subView instanceof ImageView) {
                ((ImageView) subView).setImageResource(levelGridImages[level.getLevelImage()]);
            }
        }
    }

    public void resetLevelIcons() {
        for (int i = 0; i < levelView.getChildCount(); i++) {
            final View subView = levelView.getChildAt(i);
            if (subView instanceof ImageView) {
                subView.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void resetLevelBounds() {
        level.setMinBound(level.getMIN_BOUNDS_DEFAULT());
        level.setMaxBound(level.getMAX_BOUNDS_DEFAULT());
    }

    public void changeLevelIcon(){
        if(level.getLevelImage()< levelGridImages.length){
            level.setLevelImage(level.getLevelImage()+1);
        }
    }

    public String getGeneralLevel(){
        return String.valueOf(level.getGeneralLevelNum());
    }

    public void setGeneralLevel(){
        levelNumtextView.setText(String.valueOf(level.getGeneralLevelNum()));
    }

    public void upGeneralLevel(){
        level.setGeneralLevelNum(level.getGeneralLevelNum() + 1);
        setGeneralLevel();
    }

    public void resetGeneralLevel(){
        level.setGeneralLevelNum(0);
        setGeneralLevel();
    }
}
