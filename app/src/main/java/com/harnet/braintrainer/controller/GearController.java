package com.harnet.braintrainer.controller;

import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class GearController {
    private static final String TAG = "GearController";
    private ImageView gearImageView;

    //TODO Create an array with images for spin (gear, brain, etc)

    public GearController(ImageView gearImageView) {
        this.gearImageView = gearImageView;
    }


    public void startSpinning(int spinDuration){
        gearImageView.animate().rotationBy(7200).translationXBy(-40).translationYBy(+350).setDuration(spinDuration*1000)
                .setInterpolator(new LinearInterpolator());
    }

    public void cancelPosition(){
        gearImageView.animate().rotationBy(-360).translationXBy(40).translationYBy(-350).setDuration(500);
    }

    public void changeGearImageView(int[] levelImages, int levelImage){
        Log.d(TAG, "levelImages/levelImage : " + levelImages.length + "/" + levelImage);
        if(levelImage < levelImages.length){
            gearImageView.setImageResource(levelImages[levelImage]);
        }
    }
}
