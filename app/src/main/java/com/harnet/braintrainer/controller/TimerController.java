package com.harnet.braintrainer.controller;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Timer;

public class TimerController {

    private static final String TAG = "timerController";
    private Timer timer;
    private TextView timerView;
    private GridLayout answerGridLayout;
    private int restTime; // time for doing assignment
    private int countDownInterval = 1000; // count in seconds

    public TimerController(Timer timer, TextView timerView, GridLayout answerGridLayout) {
        this.timer = timer;
        this.timerView = timerView;
        this.answerGridLayout = answerGridLayout;
        this.restTime = timer.getDuration();
        timerView.setText(String.valueOf(restTime));
    }

    // start timer
    public void startTimer(final TextView taskTextView, final Button goBtn, final ScoreController scoreController){
        new CountDownTimer(restTime * 1000, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                restTime -= countDownInterval/1000;
                timerView.setText(String.valueOf(restTime));
                if(restTime < 1){
                    answerGridLayout.setVisibility(View.INVISIBLE); // prevent input after game finish
                }
            }

            @Override
            public void onFinish() {
                answerGridLayout.setVisibility(View.INVISIBLE);
                Log.i(TAG, "onFinish: time is up");
                goBtn.setVisibility(View.VISIBLE);
                taskTextView.setText("Again?");
                resetTimer();// reset timer
//                scoreController.resetScore();
            }
        }.start();
    }

    //reset timer
    public void resetTimer(){
        restTime = timer.getDuration();
        timerView.setText(String.valueOf(restTime));
    }
}
