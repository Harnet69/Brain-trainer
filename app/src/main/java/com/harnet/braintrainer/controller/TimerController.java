package com.harnet.braintrainer.controller;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Game;
import com.harnet.braintrainer.model.Timer;

public class TimerController {

    private static final String TAG = "timerController";
    private Timer timer;
    private TextView timerView;
    private GridLayout answerGridLayout;
    private GearController gearController;
    private int restTime; // time for doing assignment
    private int countDownInterval = 1000; // count in seconds
    private int timerViewTextColor;
    private LevelController levelController; // control level of game
    private ScoreController scoreController;

    public TimerController(Timer timer, TextView timerView, GridLayout answerGridLayout, GearController gearController, LevelController levelController, ScoreController scoreController) {
        this.timer = timer;
        this.timerView = timerView;
        this.answerGridLayout = answerGridLayout;
        this.restTime = timer.getDuration();
        this.gearController = gearController;
        this.levelController = levelController;
        timerView.setText(String.valueOf(restTime));
        this.timerViewTextColor = timerView.getCurrentTextColor();
        this.scoreController = scoreController;
    }

    public int getTimerDuration(){
        return timer.getDuration();
    }

    // start timer
    public void startTimer(final TextView taskTextView, final ScoreController scoreController){
        new CountDownTimer(restTime * 1000, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                restTime -= countDownInterval/1000;
                timerView.setText(String.valueOf(restTime));
                if(restTime <=5){
                    timerView.setTextColor(Color.parseColor("#fc0313"));
                }
                if(restTime < 1){
                    answerGridLayout.setVisibility(View.INVISIBLE); // prevent input after game finish
                }
            }

            @Override
            public void onFinish() {
                answerGridLayout.setVisibility(View.INVISIBLE);
                Log.i(TAG, "onFinish: time is up");
                taskTextView.setText("");
                resetTimer();// reset timer
                Game.getInstance().setGame(false);
                gearController.cancelPosition();
                timerView.setTextColor(timerViewTextColor);
                //TODO something wrong here
                levelController.addNextLevel(scoreController.getRightAnswers(), scoreController.getWrongAnswers()); // add or not new  level
            }
        }.start();
    }

    //reset timer
    public void resetTimer(){
        restTime = timer.getDuration();
        timerView.setText(String.valueOf(restTime));
    }
}
