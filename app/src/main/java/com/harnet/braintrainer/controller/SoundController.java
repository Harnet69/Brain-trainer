package com.harnet.braintrainer.controller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

import com.harnet.braintrainer.model.Sounds;

public class SoundController extends Service {
    private Context mContext;
    private float speed = 1.00f;
    private MediaPlayer bgrSound;
    private MediaPlayer rightAnswerSound;
    private MediaPlayer wrongAnswerSound;
    private MediaPlayer nextLevelSound;
    private MediaPlayer nextGeneralLevelSound;

    public SoundController(Context mContext) {
        this.mContext = mContext;
        rightAnswerSound = MediaPlayer.create(mContext, Sounds.ANSWER_TRUE.getSound());
        wrongAnswerSound = MediaPlayer.create(mContext, Sounds.ANSWER_FALSE.getSound());
        nextLevelSound = MediaPlayer.create(mContext, Sounds.NEXT_LEVEL.getSound());
        nextGeneralLevelSound = MediaPlayer.create(mContext, Sounds.NEXT_GENERAL_LEVEL.getSound());
        onCreate();
    }

    public MediaPlayer getBgrSound() {
        return bgrSound;
    }

    public MediaPlayer getRightAnswerSound() {
        return rightAnswerSound;
    }

    public MediaPlayer getWrongAnswerSound() {
        return wrongAnswerSound;
    }

    public MediaPlayer getNextLevelSound() {
        return nextLevelSound;
    }

    public MediaPlayer getNextGeneralLevelSound() {
        return nextGeneralLevelSound;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    public void onCreate()
    {
        bgrSound = MediaPlayer.create(mContext, Sounds.BACKGROUND_MUSIC.getSound());
        bgrSound.setLooping(true);
    }
    public void onDestroy() {
        bgrSound.stop();
        resetSpeed();
    }
    public void onPause()
    {
        if(bgrSound != null) {
            bgrSound.pause();
        }
    }
    public void onStart(){
        if(bgrSound != null){
            bgrSound.start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void speedUp(){
        speed += 0.2;
        if(bgrSound.isPlaying()){
            bgrSound.setPlaybackParams(bgrSound.getPlaybackParams().setSpeed(speed));
        }
    }

    public void resetSpeed(){
        speed = 1;
    }
}
