package com.harnet.braintrainer.controller;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.harnet.braintrainer.R;

public class GearController {
    private ImageView gearImageView;
    private boolean isInitPos = true;

    //TODO Create an array with images for spin (gear, brain, etc)

    public GearController(ImageView gearImageView) {
        this.gearImageView = gearImageView;
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

    public void winGearImageView(){
        gearImageView.setImageResource(R.drawable.brain);
    }
}
