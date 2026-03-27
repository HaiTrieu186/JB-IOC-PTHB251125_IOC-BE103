package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.RoleEnum;
import com.example.taskmanagement.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("U1", "Nguyen Van A", "nva@gmail.com", RoleEnum.MEMBER));
        users.add(new User("U2", "Tran Thi B", "ttb@gmail.com", RoleEnum.LEADER));
        users.add(new User("U3", "Le Van C", "lvc@gmail.com", RoleEnum.MEMBER));
    }

    public List<User> findAll(){
        return users;
    };
}
