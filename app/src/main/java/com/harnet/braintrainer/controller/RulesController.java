package com.harnet.braintrainer.controller;
import android.widget.TextView;

public class RulesController {
    private final int RIGHT_ANSWERS_PERCENTAGE = 20;
    private final int MIN_RIGHT_ANSWERS_QUANTITY = 7;
    private final int MAX_WRONG_ANSWERS_QUANTITY = 7;


    // check result of single task
    public boolean checkSingleResult(TextView textView, int rightResult) {
        return Integer.parseInt((String) textView.getTag()) == rightResult;
    }

    // wrong answers <=3 and <10% of all answers & rightAnswers >=7
    public boolean checkGameSessionResult(int rightAnswers, int wrongAnswers){
        if(rightAnswers == 0 && wrongAnswers == 0){
            return false;
        }
        return (wrongAnswers*100)/(rightAnswers+wrongAnswers) <= RIGHT_ANSWERS_PERCENTAGE &&
                wrongAnswers <=MAX_WRONG_ANSWERS_QUANTITY && rightAnswers >= MIN_RIGHT_ANSWERS_QUANTITY ;
    }
}