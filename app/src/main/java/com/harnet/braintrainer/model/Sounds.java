package com.harnet.braintrainer.model;

import androidx.annotation.NonNull;

import com.harnet.braintrainer.R;

public enum Sounds {
    ANSWER_TRUE(R.raw.answer_true),
    ANSWER_FALSE(R.raw.answer_false),
    NEXT_LEVEL(R.raw.next_level),
    NEXT_GENERAL_LEVEL(R.raw.next_general_level),
    BACKGROUND_MUSIC(R.raw.game_processing_sound);

    private int sound;

    Sounds(int kind) {
        this.sound = kind;
    }

    public int getSound() {
        return sound;
    }
}
