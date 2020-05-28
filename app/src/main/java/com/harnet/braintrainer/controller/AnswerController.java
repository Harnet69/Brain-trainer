package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.ImageView;

import androidx.gridlayout.widget.GridLayout;

public class AnswerController {


    public void generateAnswers(int rightResult){
        System.out.println(rightResult);// TODO generate tags with results for four cells one is correct
    }

    // initial adding a tag with cell number to ImageView
//    public void addTagsToCells() {
//        GridLayout yourLayout = findViewById(R.id.gridLayout);
////        Log.i("Num col", String.valueOf(yourLayout.getColumnCount()));  // OOP test
//        gameState = new int[yourLayout.getChildCount()];
//        for (int i = 0; i < yourLayout.getChildCount(); i++) {
//            View subView = yourLayout.getChildAt(i);
//            if (subView instanceof ImageView) {
//                ImageView imageView = (ImageView) subView;
//                imageView.setTag(i);
//                gameState[i] = 0;
//            }
//        }
//    }
}
