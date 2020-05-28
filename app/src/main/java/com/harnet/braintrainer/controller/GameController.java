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
    private int rightResult;

    public GameController(TextView taskTextView, TextView timerTextView, TextView scoreTextView, Button goBtn, GridLayout answerGridLayout) {
        this.goBtn = goBtn;
        this.taskTextView = taskTextView;
        this.answerGridLayout = answerGridLayout;
        taskController = new TaskController(taskTextView);
        timerController = new TimerController(new Timer(duration), timerTextView, answerGridLayout);
        scoreController = new ScoreController(scoreTextView);
        answerController = new AnswerController(answerGridLayout);
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
//                scoreController.addScore(false);//test score system WORKS
//                taskController.getTaskResult(); // WORKS
            }
        });
    }
    // TODO Implement score controller which add score after click answer

    public void checkResult(View view){
        System.out.println(view.getTag());
    }

    public void addClickListenerToBtns(){
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            final View subView = answerGridLayout.getChildAt(i);
            if (subView instanceof TextView) {
                subView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Integer.parseInt((String) subView.getTag()) == rightResult){
                            scoreController.addScore(true);
                        }else {
                            scoreController.addScore(false);
                        }
                        //TODO refresh a task
                        rightResult = taskController.showNewTask();// create a new task
                        answerController.generateAnswers(rightResult);// generate new answers
                    }
                });
            }
        }
    }
}
