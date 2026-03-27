package com.example.taskmanagement.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String id;
    private String username;
    private String email;
    private RoleEnum role;
}
