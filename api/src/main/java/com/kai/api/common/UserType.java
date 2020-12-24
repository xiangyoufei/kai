package com.kai.api.common;

public enum UserType {

    admin("管理员"),
    visitor("访客");

    private String type;

    private UserType(String type) {
        this.type = type;
    }
}
