package com.harnet.braintrainer.controller;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class ScoreController {
    private TextView scoreTextView;
    private int rightAnswers;
    private int wrongAnswers;

    public ScoreController(TextView scoreTextView) {
        this.scoreTextView = scoreTextView;
    }

    public void addScore(Boolean isAnswerRight){
        if(isAnswerRight){
            rightAnswers++;
        }else{
            wrongAnswers++;
        }
        updateScoreView();
    }

    @SuppressLint("DefaultLocale")
    private void updateScoreView(){
        scoreTextView.setText(String.format("%d/%d", rightAnswers, wrongAnswers));
    }
}
