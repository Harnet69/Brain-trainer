package com.harnet.braintrainer.controller;

import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Game;
import com.harnet.braintrainer.model.Timer;

public class TimerController {

    private static final String TAG = "timerController";
    private Timer timer;
    CountDownTimer gameTimer;
    private SoundController soundController;
    private TextView timerView;
    private TextView taskTextView;
    private GridLayout answerGridLayout;
    private GearController gearController;
    private int restTime; // time for doing assignment
    private int countDownInterval = 1000; // count in seconds
    private int timerViewTextColor;
    private LevelController levelController; // control level of game
    private ScoreController scoreController;

    public TimerController(Timer timer, TextView timerView, GridLayout answerGridLayout, GearController gearController,
                           LevelController levelController, ScoreController scoreController,
                           SoundController soundController, TextView taskTextView) {
        this.timer = timer;
        this.timerView = timerView;
        this.taskTextView = taskTextView;
        this.answerGridLayout = answerGridLayout;
        this.restTime = timer.getDuration();
        this.gearController = gearController;
        this.levelController = levelController;
        timerView.setText(String.valueOf(restTime));
        this.timerViewTextColor = timerView.getCurrentTextColor();
        this.scoreController = scoreController;
        this.soundController = soundController;
    }

    public int getTimerDuration() {
        return timer.getDuration();
    }

    // start timer
    public void startTimer() {
        soundController.onStart();
        gameTimer = new CountDownTimer(restTime * 1000, countDownInterval) {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTick(long millisUntilFinished) {
                restTime -= countDownInterval / 1000;
                timerView.setText(String.valueOf(restTime));
                if (restTime <= 5) {
                    timerView.setTextColor(Color.parseColor("#fc0313"));
                    soundController.speedUp();
                }
                if (restTime < 1) {
                    answerGridLayout.setVisibility(View.INVISIBLE); // prevent input after game finish
                }
            }

            @Override
            public void onFinish() {
                answerGridLayout.setVisibility(View.INVISIBLE);
                taskTextView.setText("");
                resetTimer();// reset timer
                Game.getInstance().setGame(false);
                gearController.cancelPosition();
                timerView.setTextColor(timerViewTextColor);
                boolean levelPassed = levelController.addNextLevel(scoreController.getRightAnswers(), scoreController.getWrongAnswers()); // add or not new  level
                // win condition
                if (levelController.getLevel().getLevelNum() == 6 && levelPassed) { //TODO HARDCODED WIN LEVEL
                    Game.getInstance().setGame(false);
                    levelController.generalLevelUp();
                    gearController.changeGearImageView(levelController.getLevelImages(), levelController.getLevel().getLevelImage()); // change image to the next
                }
                soundController.onDestroy();
            }
        }.start();
    }

    //reset timer
    public void resetTimer() {
        restTime = timer.getDuration();
        timerView.setText(String.valueOf(restTime));
    }

    public void pauseTimer(){
        gameTimer.cancel();
//        gameTimer.pause();
        // TODO implement saving rests time, invoke timer.cancel() for inPause state and timer.start() for inResume()
    }
}
