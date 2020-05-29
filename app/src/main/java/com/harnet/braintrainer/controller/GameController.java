package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Timer;

public class GameController {
    private int duration = 20;
    private Button goBtn;
    private TextView taskTextView;
    private GridLayout answerGridLayout;
    private TimerController timerController;
    private TaskController taskController;
    private ScoreController scoreController;
    private AnswerController answerController;
    private CheckController checkController;
    private int rightResult;

    public GameController(TextView taskTextView, TextView timerTextView, TextView scoreTextView, Button goBtn, GridLayout answerGridLayout) {
        this.goBtn = goBtn;
        this.taskTextView = taskTextView;
        this.answerGridLayout = answerGridLayout;
        taskController = new TaskController(taskTextView);
        timerController = new TimerController(new Timer(duration), timerTextView, answerGridLayout);
        scoreController = new ScoreController(scoreTextView);
        answerController = new AnswerController(answerGridLayout);
        checkController = new CheckController();
    }

    public void startGame() {
        goBtn.setOnClickListener(null);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBtn.setVisibility(View.INVISIBLE);
                answerGridLayout.setVisibility(View.VISIBLE);
                timerController.startTimer(taskTextView, goBtn, scoreController);// start the countDown timer
                rightResult = taskController.showNewTask();// create a new task
                answerController.generateAnswers(rightResult); // TODO generate results with one right result
                addClickListenerToBtns();
                scoreController.resetScore();
//                scoreController.addScore(false);//test score system WORKS
//                taskController.getTaskResult(); // WORKS
            }
        });
    }
    // TODO Implement score controller which add score after click answer


    // add click listeners to buttons
    public void addClickListenerToBtns(){
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            final View subView = answerGridLayout.getChildAt(i);
            if (subView instanceof TextView) {
                subView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serveAMove((TextView) subView);
                    }
                });
            }
        }
    }

    // preform a move
    private void serveAMove(TextView viewWithResult){
        boolean isAnswerRight = checkController.checkResult(viewWithResult, rightResult);//check users move
        scoreController.addScore(isAnswerRight);// add a score
        rightResult = taskController.showNewTask();// create a new task
        answerController.generateAnswers(rightResult);// generate new answers
    }
}
