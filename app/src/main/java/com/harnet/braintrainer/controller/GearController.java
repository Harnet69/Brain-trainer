package com.harnet.braintrainer.controller;

import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.model.Level;

public class GearController {
    private static final String TAG = "GearController";
    private ImageView gearImageView;
    private boolean isInitPos = true;
    private int levelImage;
    private int[] levelImages;

    //TODO Create an array with images for spin (gear, brain, etc)

    public GearController(ImageView gearImageView, int levelImage, int[] levelImages) {
        this.gearImageView = gearImageView;
        this.levelImages = levelImages;
        this.levelImage = levelImage;
    }

    public boolean isInitPos() {
        return isInitPos;
    }

    public void setInitPos(boolean initPos) {
        isInitPos = initPos;
    }

    public void startSpinning(int spinDuration){
        gearImageView.animate().rotationBy(3600).translationXBy(-40).translationYBy(+350).setDuration(spinDuration*1000)
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
