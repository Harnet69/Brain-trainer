package com.harnet.braintrainer.model;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Task {
    private static final String TAG = "Task";
    private int num1;
    private int num2;
    private String operator;

    // generate a task
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DefaultLocale")
    public String generateTask(int minBound, int maxBound){
        String[] operators = new String[]{"+", "-", "*", "/"};
        String taskString = "";
        Random rand = new Random();
//        this.num1 = rand.nextInt(10); // TODO here you can implement the difficulty of a game
//        this.num2 = rand.nextInt(10);
        Log.d(TAG, "generateTask: "+minBound + " : " +maxBound);
        this.num1 = ThreadLocalRandom.current().nextInt(minBound, maxBound + 1);
        this.num2 = ThreadLocalRandom.current().nextInt(minBound, maxBound + 1);
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
//            num1 = (num1 == 0)? 1 : tempNum; // cause a app crash
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
