package com.harnet.braintrainer.controller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.harnet.braintrainer.R;

public class SoundBackgroundController extends Service {
    private static final String TAG = "SoundBackgroundControll";
    private int backgroundSound;
    private Context mContext;

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
//        mp.start();
    }
    public void onDestroy()
    {

        mp.stop();
    }
    public void onStart(){

        Log.d(TAG, "On start");
        mp.start();
    }
}
