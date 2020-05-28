package com.harnet.braintrainer.controller;

import android.view.View;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AnswerController {
    GridLayout answerGridLayout;

    public AnswerController(GridLayout answerGridLayout) {
        this.answerGridLayout = answerGridLayout;
    }

    public void generateAnswers(int rightResult){
        System.out.println(rightResult);// TODO generate tags with results for four cells one is correct
        int[] answers = new int[answerGridLayout.getChildCount()];
        answers = fillAnswersArray(answers, rightResult);
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            View subView = answerGridLayout.getChildAt(i);
            if (subView instanceof TextView) {
                ((TextView) subView).setText(String.valueOf(answers[i]));
                subView.setTag(String.valueOf(answers[i]));
            }
        }
    }

    private int[] fillAnswersArray(int[] answers, int rightResult){
        Random rand = new Random();
        int maxBound = (int) ((Math.abs(rightResult)*1.2)+rand.nextInt(10));
        for(int i = 0; i < answers.length; i++){
            if(i == 0){
                answers[i] = rightResult;
            }else{
                answers[i] = rand.nextInt(maxBound);
                if(isNumInArray(answers, answers[i])){
                    i--;
                }
            }
        }
        return shuffleAnswers(answers);
    }

    // shuffle answers array
    private int[] shuffleAnswers(int[] answers){
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

    private boolean isNumInArray(int[] answers, int i){
        for(int num : answers){
            return i == num;
        }
        return false;
    }
}
