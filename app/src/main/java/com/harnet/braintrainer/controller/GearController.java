package com.harnet.braintrainer.controller;

import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class GearController {
    private ImageView gearImageView;

    public GearController(ImageView gearImageView) {
        this.gearImageView = gearImageView;
    }

    public void startSpinning(int spinDuration){
        gearImageView.animate().rotationBy(720).setDuration(spinDuration*1000)
                .setInterpolator(new LinearInterpolator());
    }
}
