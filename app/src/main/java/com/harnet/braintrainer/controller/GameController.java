package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.model.Game;
import com.harnet.braintrainer.model.Timer;

public class GameController {
    private Game game;
    private Button goBtn;
    private TextView taskTextView;
    private TimerController timerController;
    private TaskController taskController;

    public GameController(Game game, TextView taskTextView, TextView timerTextView, Button goBtn) {
        this.game = new Game();
        this.goBtn = goBtn;
        this.taskTextView = taskTextView;
        taskController = new TaskController(taskTextView);
        timerController = new TimerController(new Timer(30), timerTextView);
    }

    public void startGame() {
        goBtn.setOnClickListener(null);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBtn.setVisibility(View.INVISIBLE);
                timerController.startTimer();// start the countDown timer
                taskController.showNewTask();// create a new task
            }
        });
    }

    public void gameOver(){
        game.setGameFinish(true);
        goBtn.setVisibility(View.VISIBLE);
        timerController.resetTimer();// reset timer
        taskTextView.setText("");// clear Task View field
    }
}
