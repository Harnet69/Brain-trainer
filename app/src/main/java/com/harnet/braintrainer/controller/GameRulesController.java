package com.harnet.braintrainer.controller;
import android.widget.TextView;

public class GameRulesController {
    private TextView textView;
    private int rightResult;

    public boolean checkResult(TextView textView, int rightResult) {
        return (Integer.parseInt((String) textView.getTag()) == rightResult);
    }
}