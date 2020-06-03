package com.harnet.braintrainer.controller;

import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.gridlayout.widget.GridLayout;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AnswerController {
    private static final String TAG = "AnswerController";
    private GridLayout answerGridLayout;

    public AnswerController(GridLayout answerGridLayout) {
        this.answerGridLayout = answerGridLayout;
    }

    // add texts and tags to buttons with numbers from answers array
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void generateAnswers(int rightResult) {
        int[] answers = new int[answerGridLayout.getChildCount()];
        // TODO check the quantity of childs
        answers = fillAnswersArray(answers, rightResult);
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            View subView = answerGridLayout.getChildAt(i);
            if (subView instanceof TextView) {
                ((TextView) subView).setText(String.valueOf(answers[i]));
                subView.setTag(String.valueOf(answers[i]));
                subView.setSoundEffectsEnabled(false);
            }
        }
    }

    // add to answers array one correct answer and mix it with incorrect
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int[] fillAnswersArray(int[] answers, int rightResult) {
        answers[0] = rightResult;
        int attempts = 0;
        for (int i = 1; i < answers.length; i++) {
            int wrongAnswer = ThreadLocalRandom.current().nextInt((rightResult / 2) - 5, Math.abs((rightResult * 2) + 5));
            while (isNumInArray(answers, wrongAnswer) && attempts <=3) {
                wrongAnswer = ThreadLocalRandom.current().nextInt((rightResult / 2) - 5, Math.abs((rightResult * 2) + 5));
                attempts++;
            }
            Log.d(TAG, "fillAnswersArray: " + wrongAnswer);
            answers[i] = wrongAnswer+1;
            attempts = 0;
        }
        return shuffleAnswers(answers);
    }

    // shuffle answers array
    private int[] shuffleAnswers(int[] answers) {
        Random rand = new Random();
        int answersLength = answers.length;
        for (int i = 0; i < answersLength; i++) {
            int randomIndexToSwap = rand.nextInt(answersLength);
            int temp = answers[randomIndexToSwap];
            answers[randomIndexToSwap] = answers[i];
            answers[i] = temp;
        }
        return answers;
    }

    // check if all elements are unique
    private boolean isNumInArray(int[] answers, int answer) {
        for (int value : answers) {
            if(value == answer){ // TODO cause of a crash
                return true;
            }
        }
        return false;
    }
}