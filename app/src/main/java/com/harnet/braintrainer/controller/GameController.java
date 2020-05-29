package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Timer;

public class GameController {
    private int duration = 20;
    private Button goBtn;
    private TextView taskTextView;
    private GridLayout answerGridLayout;
    private ImageView gearImageView;
    private TimerController timerController;
    private TaskController taskController;
    private ScoreController scoreController;
    private AnswerController answerController;
    private GameRulesController checkController;
    private GearController gearController;
    private int rightResult;

    public GameController(TextView taskTextView, TextView timerTextView, TextView scoreTextView, Button goBtn, GridLayout answerGridLayout, ImageView gearImageView) {
        this.goBtn = goBtn;
        this.taskTextView = taskTextView;
        this.answerGridLayout = answerGridLayout;
        this.gearImageView = gearImageView;
        taskController = new TaskController(taskTextView);
        timerController = new TimerController(new Timer(duration), timerTextView, answerGridLayout);
        scoreController = new ScoreController(scoreTextView);
        answerController = new AnswerController(answerGridLayout);
        checkController = new GameRulesController();
        gearController = new GearController(gearImageView);
    }

    public void startGame() {
        goBtn.setOnClickListener(null);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    // new game start
    private void startNewGame(){
        goBtn.setVisibility(View.INVISIBLE);
        answerGridLayout.setVisibility(View.VISIBLE);
        timerController.startTimer(taskTextView, goBtn, scoreController);// start the countDown timer
        rightResult = taskController.showNewTask();// create a new task and return the result
        answerController.generateAnswers(rightResult); // T
        addClickListenerToBtns();
        scoreController.resetScore();
        gearController.startSpinning(timerController.getTimerDuration());
    }

    // add click listeners to buttons
    public void addClickListenerToBtns(){
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            final View subView = answerGridLayout.getChildAt(i);
            if (subView instanceof TextView) {
                subView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serveMovement((TextView) subView);
                    }
                });
            }
        }
    }


    // preform a move
    private void serveMovement(TextView viewWithResult){
        boolean isAnswerRight = checkController.checkResult(viewWithResult, rightResult);//check users move
        scoreController.addScore(isAnswerRight);// add a score
        rightResult = taskController.showNewTask();// create a new task
        answerController.generateAnswers(rightResult);// generate new answers
    }
}
