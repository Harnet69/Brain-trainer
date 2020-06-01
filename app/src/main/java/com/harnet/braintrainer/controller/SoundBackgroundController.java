package com.harnet.braintrainer.controller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

public class SoundBackgroundController extends Service {
    private int backgroundSound;
    private Context mContext;
    private float speed = 1.00f;
    private MediaPlayer bgrSoundMediaPlayer;

    public SoundBackgroundController(int backgroundSound, Context mContext) {
        this.backgroundSound = backgroundSound;
        this.mContext = mContext;
    }

    public MediaPlayer getBgrSoundMediaPlayer() {
        return bgrSoundMediaPlayer;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    public void onCreate()
    {
        bgrSoundMediaPlayer = MediaPlayer.create(mContext, backgroundSound);
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
