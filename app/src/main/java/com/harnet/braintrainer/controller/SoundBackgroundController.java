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

    public SoundBackgroundController(int backgroundSound, Context mContext) {
        this.backgroundSound = backgroundSound;
        this.mContext = mContext;
    }

    MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    public void onCreate()
    {
        mp = MediaPlayer.create(mContext, backgroundSound);
        mp.setLooping(true);
    }
    public void onDestroy() {
        mp.stop();
    }
    public void onPause()
    {
        mp.pause();
    }
    public void onStart(){
        if(mp != null){
            mp.start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void speedUp(){
        speed += 0.2;
        if(mp.isPlaying()){
            mp.setPlaybackParams(mp.getPlaybackParams().setSpeed(speed));
        }
    }

    public void resetSpeed(){
        speed = 1;
    }
}
