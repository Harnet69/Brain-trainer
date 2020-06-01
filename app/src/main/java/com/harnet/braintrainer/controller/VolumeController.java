package com.harnet.braintrainer.controller;

import android.content.Context;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.harnet.braintrainer.R;

public class VolumeController {
    private static final String TAG = "VolumeController";
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private SeekBar volumeControl;
    private int maxVolume;
    private int currentVolume;
    private Button muteBtnView;
    private boolean isSoundMute;

    public VolumeController(Context mContext, SeekBar volumeControl, MediaPlayer bgrSoundMediaPlayer, Button muteBtnView) {
        mediaPlayer = bgrSoundMediaPlayer;
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

        this.volumeControl = volumeControl;
        assert audioManager != null;
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        this.muteBtnView = muteBtnView;
        
        clickAction(); // TODO is it the right place for it?
        manageVolumeControl();
    }

    public boolean isSoundMute() {
        return isSoundMute;
    }

    public void manageVolumeControl() {
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume); // save a current volume level

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                muteBtnView.setBackgroundResource(R.drawable.unmute_btn);
                volumeControl.getProgressDrawable().setColorFilter(0xFF00FFFF, PorterDuff.Mode.MULTIPLY);
                currentVolume = progress;
                isSoundMute = false;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void muteSound() {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        muteBtnView.setBackgroundResource(R.drawable.mute_btn);
        volumeControl.getProgressDrawable().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        isSoundMute = true;
        Log.d(TAG, "muteSound: ");
    }

    public void unmuteSound() {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
        muteBtnView.setBackgroundResource(R.drawable.unmute_btn);
        volumeControl.getProgressDrawable().setColorFilter(0xFF00FFFF, PorterDuff.Mode.MULTIPLY);
        isSoundMute = false;
        Log.d(TAG, "unmuteSound: ");
    }

    public void clickAction() {
        muteBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSoundMute()){
                    unmuteSound();
                }else{
                    muteSound();
                }
            }
        });
    }
}
