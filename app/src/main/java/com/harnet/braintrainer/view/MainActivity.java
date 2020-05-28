package com.harnet.braintrainer.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.controller.GameController;

public class MainActivity extends AppCompatActivity {
    private Button goBtn;
    private TextView timerTextView;
    private TextView taskTextView;
    private TextView scoreTextView;

    private GameController gameController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskTextView = findViewById(R.id.taskTextView);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        goBtn = findViewById(R.id.goButton);

        gameController = new GameController(taskTextView, timerTextView, scoreTextView, goBtn);

        gameController.startGame();
    }
}
