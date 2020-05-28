package com.harnet.braintrainer.model;

import java.util.Random;

public class Task {
    private int num1;
    private int num2;
    private String[] operator;

    public Task() {
        Random rand = new Random();
        this.num1 = rand.nextInt(10);
        this.num2 = rand.nextInt(10);
        this.operator = new String[]{"+", "-", "*", "/"};
    }

    public String genetate
}
