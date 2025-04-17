package com.boic.testTask.auth;

import lombok.Data;

@Data
public class AuthRegister {
    private String username;
    private String name;
    private String surname;
    private String parentName;
    private String role;
}
