package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findAllUsers(@RequestParam(required = false) String search){
       List<User> list= userService.findAllUsers();

        if (search!=null){
            list= list.stream().filter(
                    t -> t.getUsername().toLowerCase().contains(search.toLowerCase())
            ).toList();
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

