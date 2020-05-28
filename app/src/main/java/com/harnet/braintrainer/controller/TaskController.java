package com.harnet.braintrainer.controller;

import android.widget.TextView;

import com.harnet.braintrainer.model.Task;

public class TaskController {
    private Task task;
    private TextView taskTextView;

    public TaskController(TextView taskTextView) {
        this.task = new Task();
        this.taskTextView = taskTextView;
    }

    public void showNewTask(){
        taskTextView.setText(task.generateTask());
    }
}
