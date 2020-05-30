package com.harnet.braintrainer.controller;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.model.Game;
import com.harnet.braintrainer.model.Timer;

public class GameController {
    private static final String TAG = "GameController";
    private int duration = 30; // TODO replace to 30
    private TextView taskTextView;
    private GridLayout answerGridLayout;
    private ImageView gearImageView;
    private LinearLayout levelView;
    private TimerController timerController;
    private TaskController taskController;
    private ScoreController scoreController;
    private AnswerController answerController;
    private RulesController rulesController;
    private GearController gearController; // TODO add method for changing gearImage
    private LevelController levelController;
    private int rightResult;

    public GameController(TextView taskTextView, TextView timerTextView, TextView scoreTextView, GridLayout answerGridLayout, ImageView gearImageView, LinearLayout levelView) {
        this.taskTextView = taskTextView;
        this.answerGridLayout = answerGridLayout;
        this.gearImageView = gearImageView;
        this.levelView = levelView;
        scoreController = new ScoreController(scoreTextView);
        answerController = new AnswerController(answerGridLayout);
        rulesController = new RulesController();
        levelController = new LevelController(levelView, rulesController); // level controller
        gearController = new GearController(gearImageView, levelController.getLevel().getLevelImage(), levelController.getLevelImages()); //TODO sent argument to get to level conttroller level images
        taskController = new TaskController(taskTextView, levelController.getLevel());
        timerController = new TimerController(new Timer(duration), timerTextView, answerGridLayout, gearController, levelController, scoreController);
    }

    public void startGame() {
        gearImageView.setOnClickListener(null);
        gearImageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(!Game.getInstance().isGame()){
                    startNewGame();
                    Game.getInstance().setGame(true);
                }else{
                    Log.d(TAG, "onClick: Game Over!!!");
                    // TODO game over works here!
                }
            }
        });
    }

    // new game start
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startNewGame(){
        timerController.startTimer(taskTextView, scoreController);// start the countDown timer
        answerGridLayout.setVisibility(View.VISIBLE);
        rightResult = taskController.showNewTask(levelController.getLevel().getMinBound(), levelController.getLevel().getMaxBound());// create a new task and return the result
        answerController.generateAnswers(rightResult);
        addClickListenerToBtns();
        scoreController.resetScore();
        gearController.startSpinning(timerController.getTimerDuration());
        levelController.fillLevelsByIcons();
    }

    // add click listeners to buttons
    public void addClickListenerToBtns(){
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            final View subView = answerGridLayout.getChildAt(i);
            if (subView instanceof TextView) {
                subView.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        serveMovement((TextView) subView);
                    }
                });
            }
        }
    }

    // preform a move
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void serveMovement(TextView viewWithResult){
        boolean isAnswerRight = rulesController.checkSingleResult(viewWithResult, rightResult);//check users move
        scoreController.addScore(isAnswerRight);// add a score
        rightResult = taskController.showNewTask(levelController.getLevel().getMinBound(), levelController.getLevel().getMaxBound());// create a new task //TODO hardcode
        answerController.generateAnswers(rightResult);// generate new answers
    }
}
