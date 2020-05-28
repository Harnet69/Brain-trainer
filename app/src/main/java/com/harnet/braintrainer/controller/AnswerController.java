package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

public class AnswerController {
    GridLayout answerGridLayout;

    public AnswerController(GridLayout answerGridLayout) {
        this.answerGridLayout = answerGridLayout;
    }

    public void generateAnswers(int rightResult){
        System.out.println(rightResult);// TODO generate tags with results for four cells one is correct
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            View subView = answerGridLayout.getChildAt(i);
            if (subView instanceof TextView) {
//                ImageView imageView = (ImageView) subView;
                ((TextView) subView).setText(String.valueOf(rightResult));
                subView.setTag(String.valueOf(rightResult));
//                gameState[i] = 0;
            }
        }
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
