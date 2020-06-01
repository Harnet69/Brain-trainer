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
    private MediaPlayer bgrSoundMediaPlayer;
    private MediaPlayer rightAnswer;
    private MediaPlayer wrongAnswer;
    private MediaPlayer nextLevel;
    private MediaPlayer nextGeneralLevel;

    public SoundController(Context mContext) {
        this.mContext = mContext;
        rightAnswer = MediaPlayer.create(mContext, Sounds.ANSWER_TRUE.getSound());
        wrongAnswer = MediaPlayer.create(mContext, Sounds.ANSWER_FALSE.getSound());
        nextLevel = MediaPlayer.create(mContext, Sounds.NEXT_LEVEL.getSound());
        nextGeneralLevel = MediaPlayer.create(mContext, Sounds.NEXT_GENERAL_LEVEL.getSound());
    }

    public MediaPlayer getBgrSoundMediaPlayer() {
        return bgrSoundMediaPlayer;
    }

    public MediaPlayer getRightAnswer() {
        return rightAnswer;
    }

    public MediaPlayer getWrongAnswer() {
        return wrongAnswer;
    }

    public MediaPlayer getNextLevel() {
        return nextLevel;
    }

    public MediaPlayer getNextGeneralLevel() {
        return nextGeneralLevel;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    // creame mediaplayers when onCreate state of app
    public void onCreate()
    {
        bgrSoundMediaPlayer = MediaPlayer.create(mContext, Sounds.BACKGROUND_MUSIC.getSound());
        bgrSoundMediaPlayer.setLooping(true);
    }
    public void onDestroy() {
        bgrSoundMediaPlayer.stop();
        resetSpeed();
    }
    public void onPause()
    {
        if(bgrSoundMediaPlayer != null) {
            bgrSoundMediaPlayer.pause();
        }
    }
    public void onStart(){
        if(bgrSoundMediaPlayer != null){
            bgrSoundMediaPlayer.start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void speedUp(){
        speed += 0.2;
        if(bgrSoundMediaPlayer.isPlaying()){
            bgrSoundMediaPlayer.setPlaybackParams(bgrSoundMediaPlayer.getPlaybackParams().setSpeed(speed));
        }
    }

    public void resetSpeed(){
        speed = 1;
    }
}
