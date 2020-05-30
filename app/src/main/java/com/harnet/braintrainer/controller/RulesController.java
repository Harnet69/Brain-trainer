package com.harnet.braintrainer.controller;
import android.widget.TextView;

public class RulesController {

    // check result of single task
    public boolean checkSingleResult(TextView textView, int rightResult) {
        return (Integer.parseInt((String) textView.getTag()) == rightResult);
    }

    // wrong answers <=3 and <10% of all answers & rightAnswers >=7
    public boolean checkGameSessionResult(int rightAnswers, int wrongAnswers){
        if(rightAnswers == 0 && wrongAnswers == 0){
            return false;
        }
        return (wrongAnswers*100)/(rightAnswers+wrongAnswers) <= 10 && wrongAnswers <=3 ;
        // && rightAnswers >= 7
    }
}