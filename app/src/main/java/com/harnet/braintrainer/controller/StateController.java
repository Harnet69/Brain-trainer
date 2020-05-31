package com.harnet.braintrainer.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class StateController extends Service {
    private SoundBackgroundController soundBackgroundController;

    public StateController(SoundBackgroundController soundBackgroundController) {
        this.soundBackgroundController = soundBackgroundController;
    }

    public void onPause(){
        soundBackgroundController.onPause();
        // TODO implement timer pause
    }

    public void onResume(){
        soundBackgroundController.onStart();
        // TODO implement timer start
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
