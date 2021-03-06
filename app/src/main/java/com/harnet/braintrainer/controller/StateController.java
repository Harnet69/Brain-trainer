package com.harnet.braintrainer.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.harnet.braintrainer.model.Game;

public class StateController extends Service {
    private static final String TAG = "StateController";
    private SoundController soundController;
    private TimerController timerController;
    private AnswerController answerController;
    private TaskController taskController;

    public StateController(SoundController soundController, TimerController timerController, AnswerController answerController,
                           TaskController taskController) {
        this.soundController = soundController;
        this.timerController = timerController;
        this.answerController = answerController;
        this.taskController = taskController;
    }

    public void onPause(){
        soundController.pauseBgrSound();
        timerController.pauseTimer();
        answerController.hideShowAnswers();
        taskController.showHideTask();
    }

    public void onStop(){
        soundController.pauseBgrSound();
        Game.getInstance().setGame(false);

    }

    public void onResume(){
        if (Game.getInstance().isGame()) {
            soundController.startBgrSound();
            timerController.startTimer();
            answerController.hideShowAnswers();
            taskController.showHideTask();
        }
    }

    public void onDestroy(){
        Game.getInstance().setGame(false);
        soundController.stopBgrSound();
        timerController.resetTimer();
        answerController.hideShowAnswers();
        taskController.showHideTask();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}