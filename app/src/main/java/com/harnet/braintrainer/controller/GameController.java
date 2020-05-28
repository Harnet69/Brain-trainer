package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Timer;

public class GameController {
    private int duration = 5;
    private Button goBtn;
    private TextView taskTextView;
    private TimerController timerController;
    private TaskController taskController;
    private ScoreController scoreController;
    private AnswerController answerController;
    private int rightResult;

    public GameController(TextView taskTextView, TextView timerTextView, TextView scoreTextView, Button goBtn, GridLayout answerGridLayout) {
        this.goBtn = goBtn;
        this.taskTextView = taskTextView;
        taskController = new TaskController(taskTextView);
        timerController = new TimerController(new Timer(duration), timerTextView);
        scoreController = new ScoreController(scoreTextView);
        answerController = new AnswerController();
    }

    public void startGame() {
        goBtn.setOnClickListener(null);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBtn.setVisibility(View.INVISIBLE);
                timerController.startTimer(taskTextView, goBtn);// start the countDown timer
                rightResult = taskController.showNewTask();// create a new task
                answerController.generateAnswers(rightResult); // TODO generate results with one right result
//                scoreController.addScore(false);//test score system WORKS
//                taskController.getTaskResult(); // WORKS
            }
        });
    }
    // TODO Implement score controller which add score after click answer

    public void checkResult(){

    }
}
