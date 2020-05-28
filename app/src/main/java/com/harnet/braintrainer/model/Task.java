package com.harnet.braintrainer.model;

import android.annotation.SuppressLint;

import java.util.Random;

public class Task {
    private int num1;
    private int num2;
    private String operator;

    @SuppressLint("DefaultLocale")
    public String generateTask(){
        String[] operators = new String[]{"+", "-", "*", "/"};
        String taskString = "";
        Random rand = new Random();
        this.num1 = rand.nextInt(10); // TODO here you can implement the difficulty of a game
        this.num2 = rand.nextInt(10);
        this.operator = operators[rand.nextInt(operators.length)];
        if(!operator.equals("/")){
            taskString = String.format("%d %s %d", num1, operator, num2);
        }else{
            int tempNum = num2;
            num2 *= num1;
            num1 = tempNum;
            if(num1 == 0){
                num1 = 1;
            }
            taskString = String.format("%d %s %d", num2, operator, num1);
        }

        return taskString;
    }

    // calculate the result
    public int calculateResult(){
        int result = 0;
        switch(operator){
            case "+": result = num1 + num2;
            break;
            case "-": result = num1 - num2;
            break;
            case "*": result = num1 * num2;
            break;
            case "/": result = num2 / num1;
            break;
        }
        return result;
    }
}
