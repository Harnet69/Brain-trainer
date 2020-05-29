package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Game;
import com.harnet.braintrainer.model.Timer;

public class GameController {
    private int duration = 20;
    private TextView taskTextView;
    private GridLayout answerGridLayout;
    private ImageView gearImageView;
    private LinearLayout levelView;
    private TimerController timerController;
    private TaskController taskController;
    private ScoreController scoreController;
    private AnswerController answerController;
    private GameRulesController checkController;
    private GearController gearController;
    private LevelController levelController;
    private int rightResult;

    public GameController(TextView taskTextView, TextView timerTextView, TextView scoreTextView, GridLayout answerGridLayout, ImageView gearImageView, LinearLayout levelView) {
        this.taskTextView = taskTextView;
        this.answerGridLayout = answerGridLayout;
        this.gearImageView = gearImageView;
        this.levelView = levelView;
        taskController = new TaskController(taskTextView);
        scoreController = new ScoreController(scoreTextView);
        answerController = new AnswerController(answerGridLayout);
        checkController = new GameRulesController();
        gearController = new GearController(gearImageView);
        levelController = new LevelController(levelView); // level controller
        timerController = new TimerController(new Timer(duration), timerTextView, answerGridLayout, gearController, levelController, scoreController);
    }

    public void startGame() {
        gearImageView.setOnClickListener(null);
        gearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Game.getInstance().isGame()){
                    startNewGame();
                    Game.getInstance().setGame(true);
                }
            }
        });
    }

    // new game start
    private void startNewGame(){
        answerGridLayout.setVisibility(View.VISIBLE);
        timerController.startTimer(taskTextView, scoreController);// start the countDown timer
        rightResult = taskController.showNewTask();// create a new task and return the result
        answerController.generateAnswers(rightResult);
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
        boolean isAnswerRight = checkController.checkSingleResult(viewWithResult, rightResult);//check users move
        scoreController.addScore(isAnswerRight);// add a score
        rightResult = taskController.showNewTask();// create a new task
        answerController.generateAnswers(rightResult);// generate new answers
    }
}
