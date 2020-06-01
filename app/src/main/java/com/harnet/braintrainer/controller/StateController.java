package com.harnet.braintrainer.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.harnet.braintrainer.model.Game;

public class StateController extends Service {
    private SoundController soundController;
    private TimerController timerController;

    public StateController(SoundController soundController, TimerController timerController) {
        this.soundController = soundController;
        this.timerController = timerController;
    }

    public void onPause(){
        soundController.onPause();
        timerController.pauseTimer();
    }

    public void onResume(){
        if (Game.getInstance().isGame()) {
            soundController.onStart();
            timerController.startTimer();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
