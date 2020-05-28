package com.harnet.braintrainer.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harnet.braintrainer.R;

public class MainActivity extends AppCompatActivity {
    private Button goBtn;
    private TextView timerTextView;
    private TextView taskTextView;

    private GameController gameController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskTextView = findViewById(R.id.taskTextView);
        timerTextView = findViewById(R.id.timerTextView);
        goBtn = findViewById(R.id.goButton);

        gameController = new GameController(taskTextView, timerTextView, goBtn);

        gameController.startGame();
    }
}
