package com.harnet.braintrainer.controller;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

public class ScoreController {
    private static final String TAG = "ScoreController";
    private TextView scoreTextView;
    private int rightAnswers;
    private int wrongAnswers;

    public ScoreController(TextView scoreTextView) {
        this.scoreTextView = scoreTextView;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    // add score to scores counter
    public void addScore(Boolean isAnswerRight){
        if(isAnswerRight){
            rightAnswers++;
        }else{
            wrongAnswers++;
        }
        updateScoreView();
    }

    // reset scores
    public void resetScore(){
        Log.d(TAG, "Test: resetScore: ");
        rightAnswers = 0;
        wrongAnswers = 0;
        updateScoreView();
    }

    // update scores view
    @SuppressLint("DefaultLocale")
    private void updateScoreView(){
        scoreTextView.setText(String.format("%d/%d", rightAnswers, wrongAnswers));
    }
}