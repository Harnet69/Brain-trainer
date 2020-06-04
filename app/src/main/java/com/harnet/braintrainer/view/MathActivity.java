package com.harnet.braintrainer.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.controller.GameController;
import com.harnet.braintrainer.model.Game;

public class MathActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity?";
    private TextView timerTextView;
    private TextView taskTextView;
    private TextView scoreTextView;
    private GridLayout answerGridLayout;
    private ImageView gearImageView;
    private LinearLayout levelView;
    private TextView levelNumtextView;
    private Button muteBtn;
    private SeekBar volumeControl;

    private GameController gameController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        taskTextView = findViewById(R.id.taskTextView);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerGridLayout = (GridLayout) findViewById(R.id.answerGridLayout);
        gearImageView = findViewById(R.id.gearImageView);
        levelView = findViewById(R.id.levelView);
        levelNumtextView = findViewById(R.id.levelNumtextView);
        muteBtn = findViewById(R.id.muteBtn);
        volumeControl = findViewById(R.id.volumeControl);

        gameController = new GameController(getApplicationContext(), taskTextView, timerTextView, scoreTextView,
                answerGridLayout, gearImageView, levelView, levelNumtextView, muteBtn, volumeControl);

        gameController.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.d(TAG, "onPause: ");
        gameController.getStateController().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.d(TAG, "onStop: ");
        gameController.getStateController().onPause();
//        Game.getInstance().setGame(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume: ");
        gameController.getStateController().onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "onDestroy: ");
        gameController.getStateController().onDestroy();
    }

    // back stack (go to parent arrow) controller
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}