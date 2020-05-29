package com.harnet.braintrainer.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.controller.GameController;

public class MainActivity extends AppCompatActivity {
    private Button goBtn;
    private TextView timerTextView;
    private TextView taskTextView;
    private TextView scoreTextView;
    private GridLayout answerGridLayout; // TODO find how can we cast it ti Grid Layout
    private ImageView gearImageView;

    private GameController gameController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskTextView = findViewById(R.id.taskTextView);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerGridLayout = (GridLayout) findViewById(R.id.answerGridLayout);
        goBtn = findViewById(R.id.goButton);
        gearImageView = findViewById(R.id.gearImageView);

        gameController = new GameController(taskTextView, timerTextView, scoreTextView, goBtn, answerGridLayout, gearImageView);

        gameController.startGame();
    }
}
