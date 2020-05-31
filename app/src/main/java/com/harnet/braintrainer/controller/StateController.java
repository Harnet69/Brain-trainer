package com.harnet.braintrainer.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.harnet.braintrainer.model.Game;

public class StateController extends Service {
    private SoundBackgroundController soundBackgroundController;
    private TimerController timerController;

    public StateController(SoundBackgroundController soundBackgroundController, TimerController timerController) {
        this.soundBackgroundController = soundBackgroundController;
        this.timerController = timerController;
    }

    public void onPause(){
        soundBackgroundController.onPause();
        timerController.pauseTimer();
    }

    public void onResume(){
        if (Game.getInstance().isGame()) {
            soundBackgroundController.onStart();
            timerController.startTimer();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
