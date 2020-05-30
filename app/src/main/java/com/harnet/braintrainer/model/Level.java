package com.harnet.braintrainer.model;

public class Level {
    private int levelImage;
    private int levelNum = 0;
    private int timerDuration = 20;
    private final int MIN_BOUNDS_DEFAULT = 0;
    private final int MAX_BOUNDS_DEFAULT = 5;
    private int minBound = 0;
    private int maxBound = 5;
    private int multiplicator = 2;

    public Level(int levelIco) {
        this.levelImage = levelIco;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public int getLevelImage() {
        return levelImage;
    }

    public void setLevelImage(int levelImage) {
        this.levelImage = levelImage;
    }

    public void setMinBound(int minBound) {
        this.minBound = minBound;
    }

    public void setMaxBound(int maxBound) {
        this.maxBound = maxBound;
    }

    public int getMIN_BOUNDS_DEFAULT() {
        return MIN_BOUNDS_DEFAULT;
    }

    public int getMAX_BOUNDS_DEFAULT() {
        return MAX_BOUNDS_DEFAULT;
    }

    public int getMinBound() {
        return minBound;
    }

    public int getMaxBound() {
        return maxBound;
    }

    public int getMultiplicator() {
        return multiplicator;
    }

    public void setMultiplicator(int multiplicator) {
        this.multiplicator = multiplicator;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public void up(){
        levelNum += 1;
        minBound += multiplicator;
        maxBound += multiplicator;
    }
}
