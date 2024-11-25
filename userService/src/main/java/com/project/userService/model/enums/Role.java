package com.project.userService.model.enums;

public enum Role {
    ADMINISTRATOR("admin"),
    MANAGER("manager"),
    TEACHER("teacher");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
