package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final ITaskService taskService;

    @GetMapping
    public ResponseEntity<?> findAllTasks(@RequestParam(required = false) String title){
        List<Task> list =taskService.findAllTasks();

        if (title!=null){
            list= list.stream().filter(
               t -> t.getTitle().toLowerCase().contains(title.toLowerCase())
            ).toList();
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task){
        Task createdTask = taskService.addTask(task);

        if (createdTask == null){
            return  ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

}

