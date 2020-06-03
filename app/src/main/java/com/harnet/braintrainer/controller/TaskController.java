package com.harnet.braintrainer.controller;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.harnet.braintrainer.model.Level;
import com.harnet.braintrainer.model.Task;

public class TaskController {
    private static final String TAG = "TaskController";
    private Task task;
    private TextView taskTextView;
    private Level level;

    public TaskController(TextView taskTextView, Level level) {
        this.task = new Task();
        this.taskTextView = taskTextView;
        this.level = level;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int showNewTask(int minBound, int maxBound){
        Log.d(TAG, "Test: showNewTask: ");
        taskTextView.setText(task.generateTask(minBound, maxBound));
        return task.calculateResult();
    }

    public void showHideTask(){
        if(taskTextView.isShown()){
            taskTextView.setVisibility(View.INVISIBLE);
        }else{
            taskTextView.setVisibility(View.VISIBLE);
        }
    }
}
