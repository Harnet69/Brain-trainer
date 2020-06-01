package com.harnet.braintrainer.controller;
import android.media.MediaPlayer;
import android.widget.TextView;

public class RulesController {
    private MediaPlayer rightAnswerSound;
    private MediaPlayer wrongAnswerSound;
    private MediaPlayer nextLevel;

    public RulesController(SoundController soundController) {
        this.rightAnswerSound = soundController.getRightAnswerSound();
        this.wrongAnswerSound = soundController.getWrongAnswerSound();
        this.nextLevel= soundController.getNextLevelSound();
    }

    // check result of single task
    public boolean checkSingleResult(TextView textView, int rightResult) {
        if(Integer.parseInt((String) textView.getTag()) == rightResult){
            rightAnswerSound.start();
        }else{
            wrongAnswerSound.start();
        }
        return (Integer.parseInt((String) textView.getTag()) == rightResult);
    }

    // wrong answers <=3 and <10% of all answers & rightAnswers >=7
    public boolean checkGameSessionResult(int rightAnswers, int wrongAnswers){
        if(rightAnswers == 0 && wrongAnswers == 0){
            return false;
        }
        if((wrongAnswers*100)/(rightAnswers+wrongAnswers) <= 20 && wrongAnswers <=3 && rightAnswers >= 7){
            nextLevel.start();
        }
        return (wrongAnswers*100)/(rightAnswers+wrongAnswers) <= 20 && wrongAnswers <=3 && rightAnswers >= 7 ;
    }
}