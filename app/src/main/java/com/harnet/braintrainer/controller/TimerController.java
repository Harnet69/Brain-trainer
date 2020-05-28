package com.harnet.braintrainer.controller;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harnet.braintrainer.model.Timer;

public class TimerController {

    private static final String TAG = "timerController";
    private Timer timer;
    private TextView timerView;
    private int restTime; // time for doing assignment
    private int countDownInterval = 1000; // count in seconds

    public TimerController(Timer timer, TextView timerView) {
        this.timer = timer;
        this.timerView = timerView;
        this.restTime = timer.getDuration();
        timerView.setText(String.valueOf(restTime));
    }

    // start timer
    public void startTimer(final TextView taskTextView, final Button goBtn){
        new CountDownTimer(restTime * 1000, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                restTime -= countDownInterval/1000;
                timerView.setText(String.valueOf(restTime));
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: time is up");
                goBtn.setVisibility(View.VISIBLE);
                resetTimer();// reset timer
                taskTextView.setText("Try again?");
            }
        }.start();
    }

    public void resetTimer(){
        restTime = timer.getDuration();
        timerView.setText(String.valueOf(restTime));
    }
}
