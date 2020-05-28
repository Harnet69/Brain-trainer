package com.harnet.braintrainer.model;

import android.annotation.SuppressLint;

import java.util.Random;

public class Task {
    private int num1;
    private int num2;
    private String operator;

    @SuppressLint("DefaultLocale")
    public String generateTask(){
        String[] operators = new String[]{"+", "-", "*"};
        Random rand = new Random();
        this.num1 = rand.nextInt(10);
        this.num2 = rand.nextInt(10);
        this.operator = operators[rand.nextInt(operators.length)];

        return String.format("%d %s %d", num1, operator, num2);
    }

    // calculate result
    public int calculateResult(){
        int result = 0;
        switch(operator){
            case "+": result = num1 + num2;
            break;
            case "-": result = num1 - num2;
            break;
            case "*": result = num1 * num2;
            break;
        }
        return result;
    }
}
