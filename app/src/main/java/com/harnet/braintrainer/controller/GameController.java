package com.harnet.braintrainer.controller;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
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
    private TextView levelNumtextView;
    private Button muteBtn;
    private SeekBar volumeControlView;

    private Context mContext;
    private TimerController timerController;
    private TaskController taskController;
    private ScoreController scoreController;
    private AnswerController answerController;
    private RulesController rulesController;
    private GearController gearController;
    private LevelController levelController;
    private SoundController soundController;
    private StateController stateController;
    private VolumeController volumeController;
    // TODO implement Volume controller
    private int rightResult;

    public GameController(Context mContext, TextView taskTextView, TextView timerTextView, TextView scoreTextView,
                          GridLayout answerGridLayout, ImageView gearImageView, LinearLayout levelView,
                          TextView levelNumtextView, Button muteBtn, SeekBar volumeControlView) {
        this.taskTextView = taskTextView;
        this.answerGridLayout = answerGridLayout;
        this.gearImageView = gearImageView;
        this.levelView = levelView;
        this.levelNumtextView = levelNumtextView;
        this.muteBtn = muteBtn;
        this.volumeControlView = volumeControlView;

        scoreController = new ScoreController(scoreTextView);
        soundController = new SoundController(mContext);
        answerController = new AnswerController(answerGridLayout);
        rulesController = new RulesController();
        levelController = new LevelController(levelView, rulesController, levelNumtextView, soundController); // level controller
        gearController = new GearController(gearImageView);
        taskController = new TaskController(taskTextView, levelController.getLevel());
        timerController = new TimerController(new Timer(duration), timerTextView, answerGridLayout, gearController, levelController, scoreController, soundController, taskTextView);
        stateController = new StateController(soundController, timerController, answerController, taskController); // manage app states
        volumeController = new VolumeController(mContext, volumeControlView, soundController.getBgrSound(), muteBtn);
    }

    public StateController getStateController() {
        return stateController;
    }

    public void startGame() {

        gearImageView.setOnClickListener(null);
        gearImageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(!Game.getInstance().isGame()){
//                    soundController.onCreate();
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
        timerController.startTimer();// start the countDown timer
        answerController.hideShowAnswers();
        rightResult = taskController.showNewTask(levelController.getLevel().getMinBound(), levelController.getLevel().getMaxBound());// create a new task and return the result
        answerController.generateAnswers(rightResult);
        addClickListenerToBtns();
        scoreController.resetScore();
        gearController.startSpinning(timerController.getTimerDuration());
        levelController.fillLevelsByIcons();
        soundController.startBgrSound();
        Log.d(TAG, "startNewGame: background sound???");
    }

    // add click listeners to buttons
    public void addClickListenerToBtns(){
        Log.d(TAG, "Test: addClickListenerToBtns: ");
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
        soundController.answerSound(isAnswerRight); // make an answers sound
        scoreController.addScore(isAnswerRight);// add a score
        rightResult = taskController.showNewTask(levelController.getLevel().getMinBound(), levelController.getLevel().getMaxBound());// create a new task //TODO hardcode
        answerController.generateAnswers(rightResult);// generate new answers
    }
}