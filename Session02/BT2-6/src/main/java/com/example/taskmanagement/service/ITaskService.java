package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;

import java.util.List;

public interface ITaskService {
    List<Task> findAllTasks();
}
