package com.harnet.braintrainer.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.controller.GameController;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button goBtn;
    private TextView timerTextView;
    private TextView taskTextView;
    private TextView scoreTextView;
    private GridLayout answerGridLayout;
    private ImageView gearImageView;
    private LinearLayout levelView;
    private TextView levelNumtextView;

    private GameController gameController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskTextView = findViewById(R.id.taskTextView);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerGridLayout = (GridLayout) findViewById(R.id.answerGridLayout);
        gearImageView = findViewById(R.id.gearImageView);
        levelView = findViewById(R.id.levelView);
        levelNumtextView = findViewById(R.id.levelNumtextView);

        gameController = new GameController(getApplicationContext(), taskTextView, timerTextView, scoreTextView,
                                                                    answerGridLayout, gearImageView, levelView, levelNumtextView);

        gameController.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameController.getStateController().onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        gameController.getStateController().onResume();
    }
}
