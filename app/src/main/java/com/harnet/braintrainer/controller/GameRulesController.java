package com.harnet.braintrainer.controller;
import android.widget.TextView;

public class GameRulesController {
    private TextView textView;
    private int rightResult;

    // check result of single task
    public boolean checkSingleResult(TextView textView, int rightResult) {
        return (Integer.parseInt((String) textView.getTag()) == rightResult);
    }

    // wrong answers <=3 and <10% of all answers
    public boolean checkGameSessionResult(int rightAnswers, int wrongAnswers){
        return (wrongAnswers*100)/(rightAnswers+wrongAnswers) <= 10 && wrongAnswers <=3;
    }
}