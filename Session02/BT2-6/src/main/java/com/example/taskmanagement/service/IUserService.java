package com.example.taskmanagement.service;

import com.example.taskmanagement.model.User;

import java.util.List;

public interface IUserService{
    List<User> findAllUsers();
}
