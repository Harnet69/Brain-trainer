package com.harnet.braintrainer.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harnet.braintrainer.R;
import com.harnet.braintrainer.model.Timer;

public class MainActivity extends AppCompatActivity {
    private Button goBtn;
    private TextView timerTextView;
    private TextView taskTextView;
    private TimerController timerController;
    private TaskController taskController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskTextView = findViewById(R.id.taskTextView);
        timerTextView = findViewById(R.id.timerTextView);
        taskController = new TaskController(taskTextView);
        timerController = new TimerController(new Timer(30), timerTextView);
        goBtn = findViewById(R.id.goButton);
        goGame();
    }

    private void goGame(){
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBtn.setVisibility(View.INVISIBLE);
                timerController.startTimer();
                taskController.showNewTask();
            }
        });
    }
}
