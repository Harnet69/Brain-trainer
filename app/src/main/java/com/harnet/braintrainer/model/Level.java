package com.harnet.braintrainer.model;

public class Level {
    private int levelNum = 0;
    private int timerDuration = 20;
    private int minBound = 0;
    private int maxBound = 5;
    private int multiplicator = 2;

    public int getLevelNum() {
        return levelNum;
    }

    public int getMinBound() {
        return minBound;
    }

    public int getMaxBound() {
        return maxBound;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public int getTimerDuration() {
        return timerDuration;
    }

    public void Up(){
        levelNum += 1;
        minBound += multiplicator;
        maxBound += multiplicator;
    }
}
