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
    private static final String TAG = "SoundController";
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
        bgrSound = MediaPlayer.create(mContext, Sounds.BACKGROUND_MUSIC.getSound());
            bgrSound.setLooping(true);
    }

    public MediaPlayer getBgrSound() {
        return bgrSound;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

//    public void onCreate()
//    {
//        bgrSound = MediaPlayer.create(mContext, Sounds.BACKGROUND_MUSIC.getSound());
//        bgrSound.setLooping(true);
//    }
//    public void onDestroy() {
//        bgrSound.stop();
//        resetSpeed();
//    }
//    public void onPause()
//    {
//        if(bgrSound != null) {
//            bgrSound.pause();
//        }
//    }
//    public void onStart(){
//        if(bgrSound != null){
//            bgrSound.start();
//        }
//    }

    public void startBgrSound(){
        bgrSound.start();
    }

    public void pauseBgrSound(){
        bgrSound.pause();
    }

    public void stopBgrSound(){
        bgrSound.stop();
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

    public void answerSound(boolean isAnswerRight){
        if(isAnswerRight){
            rightAnswerSound.start();
        }else{
            wrongAnswerSound.start();
        }
    }

    public void makeNextLevelSound(){
        nextLevelSound.start();
    }

    public void makeNextGeneralLevelSound(){
        nextGeneralLevelSound.start();
    }
}

